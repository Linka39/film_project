package com.linka39.service;

import com.linka39.entity.WebSite;
import com.linka39.entity.WebSiteInfo;

import java.util.List;

/**
 * 电影动态信息网址Service接口
 */
public interface WebSiteInfoService {

	/**
	 * 分页查询电影动态信息
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<WebSiteInfo> list(WebSiteInfo webSiteInfo,Integer page,Integer pageSize);

	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount(WebSiteInfo webSiteInfo);


	public List<WebSiteInfo> getByFilmId(Integer filmId);

	/**
	 * 根据电影网址id查询电影动态信息
	 * @param webSiteId
	 * @return
	 */
	public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);

    /**
     * 添加或者修改网站动态信息
     */
    public void save(WebSiteInfo webSiteInfo);
    /**
     * 删除网站动态信息
     */
    public void delete(Integer id);

}
