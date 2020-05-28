package com.linka39.repository;

import com.linka39.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 电影Respository接口
 */
//jpa方法，指定实体类型，和组件类型
    //返回键值对 对象   <实体类,id>
public interface FilmRepository extends JpaRepository<Film,Integer>,JpaSpecificationExecutor<Film> {

    /**
     * 获取上一个电影
     * @param id
     * @return
     */
    @Query(value ="SELECT * FROM t_film WHERE id <?1 ORDER BY id DESC LIMIT 1",nativeQuery = true)
    public Film getLast(Integer id);

    @Query(value ="SELECT * FROM t_film WHERE id >?1 ORDER BY id ASC LIMIT 1",nativeQuery = true)
    public Film getNext(Integer id);
}
