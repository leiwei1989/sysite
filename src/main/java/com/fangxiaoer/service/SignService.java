package com.fangxiaoer.service;

import com.fangxiaoer.common.Constants;
import com.fangxiaoer.common.HttpUtil;
import com.fangxiaoer.common.Params;
import com.fangxiaoer.common.Utils;

import com.fangxiaoer.model.LoginResultFilter;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by leiwei on 2017/4/21.
 */
@Service
public class SignService {
    public boolean simpleLogin(String user, String password, HttpSession httpSession, HttpServletResponse response){
        HashMap<String, Object> loginResult = HttpUtil.connectApi(Constants.SIMPLE_LOGIN,new Params("telNumber",user).add("pwd",password).add("memberType",1)
                .add("loginWay",1).add("loginVerion","sy.fangxiaoer.mobile").get());
        if(loginResult == null) {
            httpSession.setAttribute("muser",null);
            return false;
        }
        int status = Double.valueOf((double)loginResult.get("status")).intValue();
        if(status == 0) {
            httpSession.setAttribute("muser",null);
            return false;
        }

        if(StringUtils.isEmpty(loginResult.get("content"))) {
            httpSession.setAttribute("muser",null);
            return false;
        }
        LinkedTreeMap<String,Object> result = (LinkedTreeMap) loginResult.get("content");
        Gson gson = new Gson();
        LoginResultFilter loginResultFilter = gson.fromJson(gson.toJson(result),LoginResultFilter.class);
        Utils.addSessionAndCookie(httpSession,response,loginResultFilter,password);
        return true;
    }

    /**
     * tel验证是否存在
     * @param telNumber
     * @return
     */
    public HashMap<String,Object> mobileValidate(String telNumber){
        System.out.println("进入手机号验证页！");
        Long timeStamp = System.currentTimeMillis();
        String md5 = Utils.MD5encode("fangxiaoer#" + telNumber + timeStamp);
        HashMap<String,Object> result = (HashMap) HttpUtil.connectApi(Constants.MEMBER_VALIDATE,new Params("mobile",telNumber).add("timeMillis",timeStamp).add("md5",md5)
                .add("client","1").get());
        int status = Double.valueOf((double)result.get("status")).intValue();
        result.put("status",status);
        return result;
    }

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    public HashMap<String,Object> sendSMSCode(String mobile){
        Long timeMillis = System.currentTimeMillis();
        String md5 = Utils.MD5encode("fangxiaoer#" + mobile + timeMillis);
        HashMap<String,Object> result = HttpUtil.connectApi(Constants.SMS_CODE,new Params("mobile",mobile).add("timeMillis",timeMillis).add("md5",md5)
                .add("client","1").get());
        int status = Double.valueOf((double)result.get("status")).intValue();
        result.put("status",status);
        return result;
    }

}
