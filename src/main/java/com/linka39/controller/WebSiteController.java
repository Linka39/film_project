package com.linka39.controller;

import com.linka39.entity.Film;
import com.linka39.entity.WebSite;
import com.linka39.service.WebSiteService;
import com.linka39.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收录电影网站控制器
 */
@Controller  //一定要加上注解为Controller
@RequestMapping("webSite")
public class WebSiteController {
    @Resource
    WebSiteService webSiteService;

    /**
     * 分页查询收录网站信息
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list/{id}")
    //@PathVariable获取路径中的变量
    public ModelAndView list(@PathVariable(value = "id",required = false)Integer page) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<WebSite> webSiteList = webSiteService.list(null,page,20);
        Long total = webSiteService.getCount(null);
        mav.addObject("title","收录网站列表");
        mav.addObject("pageCode", PageUtil.genPagination("/webSite/list",total,page,20));
        mav.addObject("mainPage","webSite/list");
        mav.addObject("mainPageKey","#f");
        mav.addObject("webSiteList",webSiteList);
        mav.setViewName("index");
        return mav;
    }

}
