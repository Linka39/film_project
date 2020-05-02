package com.linka39.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 根路径以及其他其他请求处理
 */
@Controller  //一定要加上注解为Controller
public class IndexController {
    /**
     * 网页根目录请求
     */
    @RequestMapping(value = "/")
    public ModelAndView root(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title","首页");
        mav.setViewName("index");   //thymeleaf模板引擎默认返回的是.html文件
        return mav;
    }

    /**
     * 登录请求
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     * 进入后台请求
     * @return
     */
    @RequestMapping("/admin")
    public String toAdmain(){
        return "/admin/main";
    }

}
