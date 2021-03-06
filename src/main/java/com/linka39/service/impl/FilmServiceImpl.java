package com.linka39.service.impl;

import com.linka39.entity.Film;
import com.linka39.repository.FilmRepository;
import com.linka39.service.FilmService;
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
import java.util.Optional;

/**
 * 电影Service接口实现类
 */
@Service("filmService")//实现业务层逻辑 bean类的装填
public class FilmServiceImpl implements FilmService {


    @Resource //实现自动扫描装填FilmRepository类
    private FilmRepository filmRepository;
    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }

    @Override
    public List<Film> listAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film getLast(Integer id) {
        return filmRepository.getLast(id);
    }

    @Override
    public Film getNext(Integer id) {
        return filmRepository.getNext(id);
    }

    @Override
    public List<Film> list(Film film, Integer page, Integer pageSize) {
        //PageRequest不需要 new ，内部调用构造器
        Pageable pageable= PageRequest.of(page-1,pageSize, Sort.Direction.ASC,"id");
        Page<Film> pageFilm = filmRepository.findAll(new Specification<Film>() {
            //匿名内部类构造实现
            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //root获取字段名，cb实现build 动态拼接
                Predicate predicate= cb.conjunction();
                if(film!=null){
                    if(StringUtil.isNotEmpty(film.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+film.getName().trim()+"%")//加入模糊查询
                        );//获取表达式predicate
                    }if(film.getHot()!=null&&film.getHot()==1){
                        predicate.getExpressions().add(cb.equal(root.get("hot"),1)//加入模糊查询
                        );//获取表达式predicate
                    }
                    return predicate;//返回构造器
                }
                return null;
            }
        }, pageable);//内部已经封装的分页方法
        return pageFilm.getContent();
    }

    @Override
    public Optional<Film> findById(Integer id) {
       return filmRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        filmRepository.deleteById(id);
    }

    @Override
    public Long getCount(Film film) {
        Long count = filmRepository.count(new Specification<Film>() {
            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //root获取字段名，cb实现build 动态拼接
                Predicate predicate= cb.conjunction();
                if(film!=null){
                    if(StringUtil.isNotEmpty(film.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+film.getName().trim()+"%")//加入模糊查询
                        );//获取表达式predicate
                    }
                    return predicate;//返回构造器
                }
                return null;
            }
        });
        return count;
    }

    @Override
    public List<Film> randomList(Integer n) {
        return filmRepository.randomList(n);
    }
}
