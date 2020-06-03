package com.linka39.controller;

import com.linka39.entity.WebSite;
import com.linka39.entity.WebSiteInfo;
import com.linka39.service.WebSiteInfoService;
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
@RequestMapping("webSiteInfo")
public class WebSiteInfoController {
    @Resource
    WebSiteInfoService webSiteInfoService;

    /**
     * 分页查询 电影动态信息
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list/{id}")
    //@PathVariable获取路径中的变量
    public ModelAndView list(@PathVariable(value = "id",required = false)Integer page) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(null,page,20);
        Long total = webSiteInfoService.getCount(null);
        mav.addObject("title","电影网站动态信息列表");
        mav.addObject("pageCode", PageUtil.genPagination("/webSiteInfo/list",total,page,20));
        mav.addObject("mainPage","webSiteInfo/list");
        mav.addObject("mainPageKey","#f");
        mav.addObject("webSiteInfoList",webSiteInfoList);
        mav.setViewName("index");
        return mav;
    }

}
