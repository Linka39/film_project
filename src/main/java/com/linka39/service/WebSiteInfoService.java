package com.linka39.service;

import com.linka39.entity.WebSite;
import com.linka39.entity.WebSiteInfo;

import java.util.List;

/**
 * 电影动态信息网址Service接口
 */
public interface WebSiteInfoService {
    //分页查询电影动态信息
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize);

    //获取总记录数
    public Long getCount(WebSiteInfo webSiteInfo);

   /* *//**
     * 添加或者修改友情链接
     *//*
    public void save(WebSite webSite);
    *//**
     * 删除友情链接
     *//*
    public void delete(Integer id);*/
}
