package com.linka39.service;

import com.linka39.entity.Film;

import java.util.List;

/**
 * 电影Service接口
 */
public interface FilmService {
    /**
     * 添加或者修改电影
     */
    public void save(Film film);

    //分页查询收录电影信息
    public List<Film> list(Film film, Integer page, Integer pageSize);

    //获取总记录数
    public Long getCount(Film film);
}
