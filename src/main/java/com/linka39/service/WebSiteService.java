package com.linka39.service;

import com.linka39.entity.WebSite;

import java.util.List;

/**
 * 收录电影网址Service接口
 */
public interface WebSiteService {
    //分页查询收录电影网址
    public List<WebSite> list(WebSite webSite,Integer page, Integer pageSize);

    //获取总记录数
    public Long getCount(WebSite webSite);

    /**
     * 添加或者修改友情链接
     */
    public void save(WebSite webSite);
    /**
     * 删除友情链接
     */
    public void delete(Integer id);
}
