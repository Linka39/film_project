package com.linka39.repository;

import com.linka39.entity.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 电影动态信息Respository接口
 */
//jpa方法，<指定实体类型，和组件类型>
//返回键值对 对象
    //实现多继承
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo,Integer>, JpaSpecificationExecutor<WebSiteInfo> {
//JpaSpecificationExecutor进行动态查询
    //定义快速查询
   /* @Query(value = "select * from t_info where filmId = ?1",nativeQuery = true)//value 写sql
    public List<WebSiteInfo> getByFilmId(Integer filmId);*/

   /* @Query(value = "select * from t_info where filmId = ?1",nativeQuery = true)//value 写sql
    public List<WebSiteInfo> getByFilmId(Integer filmId);*/
}
