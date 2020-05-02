package com.linka39.entity;

import javax.persistence.*;

/**
 * 友情链接实体
 */
@Entity
@Table(name="t_link")  //后会自动生成t_link表
public class Link {

    @Id     //设置id为主键
    @GeneratedValue   //设置自增
    private  Integer id;  //编号

    @Column(length = 500)  //设置name长度
    private  String name;   //名称
    @Column(length = 500)
    private String url; //地址
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
