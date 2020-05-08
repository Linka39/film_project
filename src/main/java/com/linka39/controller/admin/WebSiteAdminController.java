package com.linka39.controller.admin;

import com.linka39.entity.WebSite;
import com.linka39.service.WebSiteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 收录电影网址控制层
 */
@RestController  //将map键值对自动转换为json数组形式,方便ajax交互
@RequestMapping("/admin/website")
public class WebSiteAdminController {
    @Resource
    private WebSiteService webSiteService;

    /**
     * 分页查询_收录电影网址
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(WebSite webSite, @RequestParam(value = "page",required = false)Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<WebSite> webSiteList  = webSiteService.list(webSite,page,rows);
        Long total = webSiteService.getCount(webSite);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",webSiteList);
        resultMap.put("total",total);
        return resultMap;
    }
/*    *//**
     * 添加或者修改 友情链接
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/save")
    public Map<String,Object> save(Link link)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        linkService.save(link);
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
            linkService.delete(Integer.parseInt(each));
        }
        resultMap.put("success",true);
        return resultMap;
    }*/
}
