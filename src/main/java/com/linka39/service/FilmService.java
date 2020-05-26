package com.linka39.service;

import com.linka39.entity.Film;

import java.util.List;
import java.util.Optional;

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

    //查询收录电影信息
    public List<Film> listAll();

    //获取总记录数
    public Long getCount(Film film);

    //根据id查找实体
    public Optional<Film> findById(Integer id);

    //删除信息
    public void delete(Integer id);

    /**
     * 获取上一个
     * @param id
     * @return
     */
    public Film getLast(Integer id);

    /**
     * 获取下一个电影
     * @param id
     * @return
     */
    public Film getNext(Integer id);
}
