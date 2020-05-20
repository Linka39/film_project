package com.linka39.controller.admin;

import com.linka39.entity.Film;
import com.linka39.entity.WebSite;
import com.linka39.service.WebSiteInfoService;
import com.linka39.service.WebSiteService;
import com.linka39.util.StringUtil;
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
    @Resource//一个注解只对应一个变量
    private WebSiteInfoService webSiteInfoService;

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
     /**
     * 添加或者修改 友情链接
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String,Object> save(WebSite webSite)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        webSiteService.save(webSite);
        resultMap.put("success",true);
        return resultMap;
    }
    /**
     * 删除 友情链接
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam("ids") String ids)throws Exception{
        String idsStr[] = ids.split(",");
        Map<String,Object> resultMap = new HashMap<>();
        boolean flag = true;
        Integer errorId = null;
        for(String each:idsStr){
            Integer tempId = Integer.parseInt(each);
            if(webSiteInfoService.getByWebSiteId(tempId).size()>0){
                flag=false;
                errorId = tempId;
                break;
            }else{
                webSiteService.delete(Integer.parseInt(each));
            }
        }
        if(flag){
            resultMap.put("success",true);
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","电影动态信息中存在网站信息，ID("+errorId+")不可删除");
        }
        return resultMap;
    }

    @RequestMapping("/comboList")
    //会自动转义为返回的JsonArray格式,q为自动传的
    public List<WebSite> comboList(String q) throws Exception{
        if(StringUtil.isEmpty(q)){
            return null;
        }
        WebSite webSite = new WebSite();
        webSite.setUrl(q);
        return webSiteService.list(webSite,1,30);//最多查询30条
    }
}
