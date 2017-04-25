package com.fangxiaoer.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by leiwei on 2017/4/24.
 */
@Controller
public class KaptchaController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 验证方法
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/imgvrifyControllerDefaultKaptcha",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, String code){
        HashMap<String,Object> verifyCode = new HashMap<String,Object>();
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+code);
        if (!captchaId.equals(code)) {
            verifyCode.put("info", -1);
            //andView.setViewName("index");
        } else {
            verifyCode.put("info",1);
            //andView.setViewName("succeed");
        }
        return verifyCode;
    }
}
