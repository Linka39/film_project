package com.linka39.controller.admin;

import com.linka39.entity.WebSiteInfo;
import com.linka39.service.WebSiteInfoService;
import com.linka39.service.WebSiteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 电影动态信息管理控制层
 */
@RestController  //将map键值对自动转换为json数组形式,方便ajax交互
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {
    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 分页查询_收录电影网址
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(WebSiteInfo webSiteInfo, @RequestParam(value = "page",required = false)Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<WebSiteInfo> webSiteInfoList  = webSiteInfoService.list(webSiteInfo,page,rows);
        Long total = webSiteInfoService.getCount(webSiteInfo);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",webSiteInfoList);
        resultMap.put("total",total);
        return resultMap;
    }
 /*   *//**
     * 添加或者修改 友情链接
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/save")
    public Map<String,Object> save(WebSiteInfo webSiteInfo)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        webSiteService.save(webSiteInfo);
        resultMap.put("success",true);
        return resultMap;
    }
    *//**
     * 删除 友情链接
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam("ids") String ids)throws Exception{
        String idsStr[] = ids.split(",");
        Map<String,Object> resultMap = new HashMap<>();
        for(String each:idsStr){
            webSiteService.delete(Integer.parseInt(each));
        }
        resultMap.put("success",true);
        return resultMap;
    }*/
}
