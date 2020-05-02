package com.linka39.entity;

import javax.persistence.*;

/**
 * 电影网站实体
 */
@Entity
@Table(name="t_webSite")  //后会自动生成t_link表
public class WebSite {
    @Id     //设置id为主键
    @GeneratedValue   //设置自增
    private  Integer id;  //编号

    @Column(length =100)
    private String name;    //网站名称
    @Column(length =300)
    private String url;   //网站地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
