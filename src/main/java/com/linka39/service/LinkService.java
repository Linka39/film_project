package com.linka39.service;

import com.linka39.entity.Film;
import com.linka39.entity.Link;

import java.util.List;

/**
 * 友情链接Service接口
 */
public interface LinkService {
    //分页查询友情链接
    public List<Link> list(Integer page,Integer pageSize);

    //分页查询所有友情链接
    public List<Link> listAll();

    //获取总记录数
    public Long getCount();

    /**
     * 添加或者修改友情链接
     */
    public void save(Link link);
    /**
     * 删除友情链接
     */
    public void delete(Integer id);

}
