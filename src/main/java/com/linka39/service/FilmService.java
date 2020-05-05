package com.linka39.service;

import com.linka39.entity.Film;

/**
 * 电影Service接口
 */
public interface FilmService {
    /**
     * 添加或者修改电影
     */
    public void save(Film film);
}
