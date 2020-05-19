package com.linka39.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 网站动态更新电影信息
 */
@Entity
@Table(name="t_info")  //后会自动生成t_link表
public class WebSiteInfo {
    @Id     //设置id为主键
    @GeneratedValue   //设置自增
    private  Integer id;  //编号

    @ManyToOne      //设置多对一关键
    @JoinColumn(name = "filmId")  //增加外键
    private Film film;//电影

    @ManyToOne      //设置多对一关键
    @JoinColumn(name = "webSiteId")  //增加外键
    private WebSite webSite;

    @Column(length =1000)
    private String info;        //信息
    @Column(length =500)
    private String url;     //网址

    private Date publishDate;//发出日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonSerialize(using=CustomDateSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
