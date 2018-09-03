package com.yan.shiro.controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yan.shiro.model.User;

@Controller
public class UserController {
	/**
	 * ��½
	 * @param user
	 * @return
	 */
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@Validated User user) {
//		System.out.println("UserController��login������ʼִ��");
//		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
//		System.out.println("user.getName():"+user.getName()+"user.getPassword():"+user.getPassword());	
//		try {
//			Subject subject = SecurityUtils.getSubject();
//			subject.login(token);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		return "success";
//	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@Validated User user) {
//		System.out.println("UserController��login������ʼִ��");
//		System.out.println("user.getName():"+user.getName()+"user.getPassword():"+user.getPassword());	
//		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
//		System.out.println("token�Ѿ����");
////		token.setRememberMe(true);
//		try {
//			subject.login(token);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		return "success";
//	}
	
    /**
     * ��ȡ��֤��
     * 
     * @author atd681
     * @since 2018��8��13��
     */
	@RequestMapping("/kaptcha/get")
    @ResponseBody
    public String getKaptcha(HttpSession session) {
        // Kaptcha������֤�󱣴�SESSION�е�KEYΪKAPTCHA_SESSION_KEY
        return (String) session.getAttribute("KAPTCHA_SESSION_KEY");
    }
	
    @Autowired
    private Producer captchaProducer;

    @RequestMapping(value = "/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //������֤��
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //��ͻ���д��
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
    
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		System.out.println("UserController��login������ʼִ��");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		System.out.println("token�Ѿ����");
//		token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

}
