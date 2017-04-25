package com.fangxiaoer.controller;

import com.fangxiaoer.common.Utils;
import com.fangxiaoer.common.ValidateUtil;
import com.fangxiaoer.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by leiwei on 2017/4/20.
 */
@Controller
public class SignController {
    @Autowired
    private SignService signService;

    @RequestMapping(value = "/signin")
    public String signin() {
        System.out.println("进入登录页！");
        return "home/login";
    }
    @RequestMapping(value = "/mobileValidate",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> mobileValidate(String telNumber){
        return signService.mobileValidate(telNumber);
    }
    /*
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String login(String telnumber, String url, String password, String code, Model model, HttpServletResponse response, HttpSession httpSession) {
        ValidateUtil.checkNull("telnumber", telnumber);
        String pwd = "";
        String show = "";
        if (!StringUtils.isEmpty(password)) {
            pwd = Utils.MD5encode(password);
            show = "密码";
        } else if (!StringUtils.isEmpty(code)) {
            pwd = code;
            show = "验证码";
        } else {
            model.addAttribute("error", "登录错误, 请检查填写信息");
            model.addAttribute("url",url);
            return "home/signin";
        }
        Boolean check = signService.simpleLogin(telnumber,pwd,httpSession,response);
        if (!check) {
            model.addAttribute("error", "用户名或" + show + "错误");
            model.addAttribute("url",url);
            return "home/signin";
        }
        if(!StringUtils.isEmpty(url)){
            return  "redirect:"+url;
        }
        return "home/index";
    }
    */
    @RequestMapping(value = "/signinValidate", method = RequestMethod.POST)
    public String signinValidate(String number,String password,Model model, HttpServletResponse response, HttpSession httpSession){

        System.out.println("进来了！");
        /*
        ValidateUtil.checkNull("telnumber", number);
        String pwd = "";
        String show = "";
        if (!StringUtils.isEmpty(password)) {
            pwd = Utils.MD5encode(password);
            show = "密码";
        } else {
            model.addAttribute("error", "登录错误, 请检查填写信息");
            //model.addAttribute("url",url);
            return "home/login";
        }
        Boolean check = signService.simpleLogin(number,pwd,httpSession,response);
        if (!check) {
            model.addAttribute("error", "用户名或" + show + "错误");
            //model.addAttribute("url",url);
            return "home/login";
        }
        */
        return "personal/personalPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String regist(){
        return "home/register";
    }

    @RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> sendSMSCode(String mobile){
        return signService.sendSMSCode(mobile);
    }
}
