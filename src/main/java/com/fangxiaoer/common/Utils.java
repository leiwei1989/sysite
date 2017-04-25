package com.fangxiaoer.common;


import com.fangxiaoer.model.LoginResultFilter;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tiansai on 17/3/14.
 */
public class Utils {

    public static String formatDate(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    public static String uuidGenerator() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取cookie
     * @param request
     * @param key
     * @return
     */
    public static Cookie iterationCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        Cookie cookieValue = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                cookieValue = cookie;
                break;
            }
        }
        return cookieValue;
    }

    /**
     * 添加cookie
     *
     * @param response 设置
     * @param key      用户对应cookie名称
     * @param jsonStr  发布页json数据
     */
    public static void addCookie(HttpServletResponse response, String key, String jsonStr, String cookiePath) {
        try {
            Cookie cookie = new Cookie(key, URLEncoder.encode(jsonStr, "UTF-8"));
            cookie.setPath(cookiePath);
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除Cookie
     *
     * @param key 用户对应cookie名称
     */
    public static void deleteCookie(HttpServletResponse response, String key, String cookiePath) {
        //Cookie cookie = iterationCookie(request,key);
        Cookie cookie = new Cookie(key, null);
        if (cookie != null) {
            cookie.setPath(cookiePath);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    public static Timestamp addTime(Timestamp time, String type, Integer d) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        if ("d".equals(type))
            c.add(Calendar.DATE, d); //+n天
        else if ("h".equals(type))
            c.add(Calendar.HOUR_OF_DAY, d); //+n小时
        else if ("m".equals(type))
            c.add(Calendar.MINUTE, d); //+n分钟
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(c.getTime());
        time = Timestamp.valueOf(dateTime);
        return time;
    }

    public static Timestamp currentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static boolean isNumeric(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * java对象转Map
     *
     * @param obj
     * @return Map
     */
    public static HashMap<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        HashMap<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (value == null)
                        continue;
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

    /**
     * 手机号加密和解密操作
     */
    private static String key = "k3PnA4qTdeciQ";

    public static String encode(String telNumber) {
        if (!org.springframework.util.StringUtils.isEmpty(telNumber)) {
            String pasNum = new String(Base64.getEncoder().encode(telNumber.getBytes()));
            StringBuilder sb = new StringBuilder(pasNum);
            for (int i = 0; i < key.length(); i++) {
                sb.insert(2 * i + 1, key.charAt(i));
            }
            String pasNumber = sb.toString();
            pasNumber = pasNumber.replaceAll("=", "O0O0O").replaceAll("\\+", "o000o").replaceAll("/", "oo00o");
            return pasNumber;
        }
        return "";
    }

    public static String decode(String code) {
        if (!org.springframework.util.StringUtils.isEmpty(code)) {
            code = code.replaceAll("oo00o", "/");
            code = code.replaceAll("o000o", "+");
            code = code.replaceAll("O0O0O", "=");

            StringBuilder sb = new StringBuilder(code);
            for (int i = key.length() - 1; i >= 0; i--) {
                sb.deleteCharAt(2 * i + 1);
            }
            byte[] num = Base64.getDecoder().decode(sb.toString());
            return new String(num);
        }
        return "";
    }

    /**
     * MD5加密
     * @param password
     * @return
     */
    public static String MD5encode(String password){
        return DigestUtils.md5Hex(password).toUpperCase();
    }


    public static void addSessionAndCookie(HttpSession httpSession , HttpServletResponse response, LoginResultFilter loginResultFilter, String password){
        httpSession.setAttribute("muser",loginResultFilter);
        String mobile = loginResultFilter.getMobile();
        Utils.addCookie(response,"muser",mobile,"/");
        Utils.addCookie(response,"mpassword",password,"/");
    }

}
