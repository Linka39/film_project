package com.linka39.repository;

import com.linka39.entity.Film;
import com.linka39.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 友情连接Respository接口
 */
//jpa方法，指定实体类型，和组件类型
    //返回键值对 对象
public interface LinkRepository extends JpaRepository<Link,Integer> {

}
