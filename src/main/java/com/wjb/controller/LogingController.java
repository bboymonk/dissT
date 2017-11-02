package com.wjb.controller;

import com.alibaba.druid.util.StringUtils;
import com.wjb.base.BaseController;
import com.wjb.config.shiro.ShiroKit;
import com.wjb.model.User;
import com.wjb.util.common.Common;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/10/26.
 */
@Controller
@RequestMapping("admin")
public class LogingController extends BaseController {
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("index")
    public String index(){
        return "welcome";
    }
    /**
     *登录验证
    *@Author:wjb
    *@params:
    *@Date:14:10 2017/10/26
    */
    @ResponseBody
    @PostMapping("toLogin")
    public String toLogin(User user, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        User u = (User) ShiroKit.getShiroAdmin();
        if (u != null){
            return successOrFail(false,null,"您已登录");
        }
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return successOrFail(false,null,"用户名密码不能为空");
        }
        // 添加cookie
        Cookie cookie = new Cookie("WJB", URLEncoder.encode("wjb", "UTF-8"));
        cookie.setPath("/");
        cookie.setMaxAge(Common.COOKIE_TIME);
        response.addCookie(cookie);

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return successOrFail(true,"登录成功",null);
        } catch (AuthenticationException e) {
            return successOrFail(false,null,"认证失败");
        }
    }

}
