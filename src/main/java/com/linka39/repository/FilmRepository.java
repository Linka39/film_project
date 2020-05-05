package com.linka39.repository;

import com.linka39.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 电影Respository接口
 */
//jpa方法，指定实体类型，和组件类型
    //返回键值对 对象
public interface FilmRepository extends JpaRepository<Film,Integer> {

}
