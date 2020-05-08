package com.linka39.repository;

import com.linka39.entity.Link;
import com.linka39.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 收录电影网址Respository接口
 */
//jpa方法，<指定实体类型，和组件类型>
//返回键值对 对象
    //实现多继承
public interface WebSiteRepository extends JpaRepository<WebSite,Integer>, JpaSpecificationExecutor<WebSite> {
//JpaSpecificationExecutor进行动态查询
}
