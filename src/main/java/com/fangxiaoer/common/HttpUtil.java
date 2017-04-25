package com.fangxiaoer.common;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tiansai on 17/3/14.
 */
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static HashMap<String, Object> connectApi(String url, HashMap<String, Object> params) {
        HashMap<String, Object> resultMap = new HashMap<>();
        logger.debug("Access: " + url);
        try {
            FormBody.Builder b = new FormBody.Builder();
            for (Map.Entry<String, Object> item : params.entrySet()) {
                b.add(item.getKey(), String.valueOf(item.getValue()));
            }
            RequestBody body = b.build();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            if (!StringUtils.isEmpty(json)) {
                Gson gson = new Gson();
                resultMap = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public static Object connectApi(String url, HashMap<String, Object> params, Model model) {
        HashMap<String, Object> resultMap = new HashMap<>();
        logger.warn("Access: " + url);
        try {
            FormBody.Builder b = new FormBody.Builder();
            for (Map.Entry<String, Object> item : params.entrySet()) {
                b.add(item.getKey(), String.valueOf(item.getValue()));
            }
            RequestBody body = b.build();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            if (!StringUtils.isEmpty(json)) {
                Gson gson = new Gson();
                resultMap = gson.fromJson(json, HashMap.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object result = handleResult(resultMap, model);
        return result;
    }


    private static Object handleResult(HashMap<String, Object> result, Model model) {
        if (result == null) {
            model.addAttribute("msg", "内容不存在");
            return -1;
        }
        int status = Double.valueOf((double) result.get("status")).intValue();
        if (status == 0) {
            model.addAttribute("msg", result.get("msg"));
            return -1;
        } else if (status == -1) {
            model.addAttribute("msg", result.get("msg"));
            return -2;
        }
        return result.get("content");
    }

    /**
     * service处理含有session问题的方法
     *
     * @param result 获取的结果值
     * @param column 希望将信息存入的字段名
     * @param model
     * @param key    null-结果为map，有值-结果为list
     * @return
     */
    public static Integer handleSessionResult(Object result, String column, Model model, String key) {
        if (result == null) {
            model.addAttribute(column, null);
            return 1;
        } else if (result instanceof Integer) {
            if (((Integer) result).intValue() == -2) {
                return -1;
            } else {
                return 0;
            }
        } else if (!StringUtils.isEmpty(key)) {
            List<LinkedTreeMap<String, Object>> info = (List<LinkedTreeMap<String, Object>>) result;
            model.addAttribute(column, info);
        } else {
            LinkedTreeMap<String, Object> info = (LinkedTreeMap) result;
            model.addAttribute(column, info);
        }
        return 1;
    }

    /**
     * controller处理含有session的问题
     *
     * @param url
     * @param check
     * @return
     */
    public static String handleSessionResult(String url, Integer check, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
        if (check == 1) {
            return url;
        } else if (check == 0) {
            return "error/exception";
        } else {
            session.setAttribute("muser", null);
            attributes.addAttribute("url", request.getRequestURI());
            return "redirect:/signin";
        }
    }

}
