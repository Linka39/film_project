package com.linka39.repository;

import com.linka39.entity.Film;
import com.linka39.entity.WebSite;
import com.linka39.entity.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 电影动态信息Repository接口
 * @author Administrator
 *
 */
//要提前在Jpa上加入所要返回的类型
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Integer>, JpaSpecificationExecutor<WebSiteInfo> {

    /**
     * 根据电影id查询动态信息
     * @param filmId
     * @return
     */
    @Query(value="select * from t_info where film_id=?1",nativeQuery=true)
    public List<WebSiteInfo> getByFilmId(Integer filmId);

    /**
     * 根据电影网址id查询电影动态信息
     * @param webSiteId
     * @return
     */
    @Query(value="select * from t_info where web_site_id=?1",nativeQuery=true)
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);

    /**
     * 校验电影和网址
     * @param s_name
     * @return
     */
    @Query(value="select * from t_film where name=?1",nativeQuery=true)
    public List<Film> getByFilmName(String s_name);

    @Query(value="select id from t_film where name=?1",nativeQuery=true)
    public Integer getByFilmIdName(String s_name);

    @Query(value="select id from t_web_site where url=?1",nativeQuery=true)
    public Integer getByWebSiteUrl(String s_url);
}
