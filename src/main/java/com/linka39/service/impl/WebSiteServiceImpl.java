package com.linka39.service.impl;

import com.linka39.entity.WebSite;
import com.linka39.repository.WebSiteRepository;
import com.linka39.service.WebSiteService;
import com.linka39.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 收录电影网址Service接口实现类
 */
@Service("WebSiteService")//实现业务层逻辑 bean类的装填
public class WebSiteServiceImpl implements WebSiteService {
 @Resource//注入
    private WebSiteRepository webSiteRepository;

    @Override
    public void save(WebSite webSite) {
        webSiteRepository.save(webSite);
    }

    @Override
    public void delete(Integer id) {
        webSiteRepository.deleteById(id);
    }

    @Override
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize) {
        //PageRequest不需要 new ，内部调用构造器
        Pageable pageable=PageRequest.of(page-1,pageSize,Sort.Direction.ASC,"id");
        Page<WebSite> pageWebsite = webSiteRepository.findAll(new Specification<WebSite>() {
            //匿名内部类构造实现
            @Override
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //root获取字段名，cb实现build 动态拼接
                Predicate predicate= cb.conjunction();
                if(webSite!=null){
                    if(StringUtil.isNotEmpty(webSite.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+webSite.getName().trim()+"%")//加入模糊查询
                                );//获取表达式predicate
                    }
                    if(StringUtil.isNotEmpty(webSite.getUrl())){
                        predicate.getExpressions().add(cb.like(root.get("url"),"%"+webSite.getUrl().trim()+"%")//加入模糊查询
                        );//获取表达式predicate
                    }
                    return predicate;//返回构造器
                }
                return null;
            }
        }, pageable);//内部已经封装的分页方法
        return pageWebsite.getContent();
    }

    @Override
    public List<WebSite> newestList(Integer page, Integer pageSize) {
        Pageable pageable=PageRequest.of(page-1,pageSize,Sort.Direction.DESC,"id");
        return webSiteRepository.findAll(pageable).getContent();
    }

    @Override
    public Long getCount(WebSite webSite) {
        Long count = webSiteRepository.count(new Specification<WebSite>() {
            @Override //匿名内部类
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //root获取字段名，cb实现build 动态拼接
                Predicate predicate= cb.conjunction();
                if(webSite!=null){
                    if(StringUtil.isNotEmpty(webSite.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+webSite.getName().trim()+"%")//加入模糊查询
                        );//获取表达式predicate
                    }
                    if(StringUtil.isNotEmpty(webSite.getUrl())){
                        predicate.getExpressions().add(cb.like(root.get("url"),"%"+webSite.getUrl().trim()+"%")//加入模糊查询
                        );//获取表达式predicate
                    }
                    return predicate;//返回构造器
                }
                return null;
            }
        });
        return count;
    }

}
