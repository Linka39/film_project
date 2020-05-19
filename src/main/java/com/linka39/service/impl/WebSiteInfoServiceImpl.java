package com.linka39.service.impl;

import com.linka39.entity.WebSite;
import com.linka39.entity.WebSiteInfo;
import com.linka39.repository.WebSiteInfoRepository;
import com.linka39.service.WebSiteInfoService;
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
 * 电影动态信息Service接口实现类
 */
@Service("WebSiteInfoService")//实现业务层逻辑 bean类的装填
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    @Resource
    private WebSiteInfoRepository webSiteInfoRepository;

/*    @Override
    public void save(WebSite webSite) {
        webSiteInfoRepository.save(webSite);
    }

    @Override
    public void delete(Integer id) {
        webSiteInfoRepository.deleteById(id);
    }*/

    @Override
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize) {
        Pageable pageable=PageRequest.of(page, pageSize,Sort.Direction.DESC,"publishDate");
        Page<WebSiteInfo> pageWebSite=webSiteInfoRepository.findAll(new Specification<WebSiteInfo>() {

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate=cb.conjunction();
                if(webSiteInfo!=null){
                    if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
                        predicate.getExpressions().add(cb.like(root.get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return pageWebSite.getContent();
    }

    @Override
    public Long getCount(WebSiteInfo webSiteInfo) {
        Long count=webSiteInfoRepository.count(new Specification<WebSiteInfo>() {

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate=cb.conjunction();
                if(webSiteInfo!=null){
                    if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
                        predicate.getExpressions().add(cb.like(root.get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

}
