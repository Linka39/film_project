package com.linka39.controller.admin;

import com.linka39.entity.Film;
import com.linka39.entity.Link;
import com.linka39.service.FilmService;
import com.linka39.service.LinkService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 友情链接控制层
 */
@RestController  //将map键值对自动转换为json数组形式,方便ajax交互
@RequestMapping("/admin/link")
public class LinkAdminController {
    @Resource
    private LinkService linkService;

    /**
     * 分页查询友情链接
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<Link> linkList = linkService.list(page-1,rows);
        Long total = linkService.getCount();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",linkList);
        resultMap.put("total",total);
        return resultMap;
    }
    /**
     * 添加或者修改 友情链接
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String,Object> save(Link link)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        linkService.save(link);
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
        for(String each:idsStr){
            linkService.delete(Integer.parseInt(each));
        }
        resultMap.put("success",true);
        return resultMap;
    }
}
