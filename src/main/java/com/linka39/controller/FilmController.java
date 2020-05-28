package com.linka39.controller;

import com.linka39.entity.Film;
import com.linka39.entity.Link;
import com.linka39.entity.WebSiteInfo;
import com.linka39.service.FilmService;
import com.linka39.service.WebSiteInfoService;
import com.linka39.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * 电影搜索Comtroller类
 */
@Controller  //一定要加上注解为Controller
@RequestMapping("/film")
public class FilmController {
    @Resource
    FilmService filmService;
    @Resource
    WebSiteInfoService webSiteInfoService;

    /**
     * 模糊查询搜索电影
     * @param s_film
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/search")
    //@Valid 用于验证实体上的注解，BindingResult用于获取校验错误信息
    public ModelAndView search(@Valid Film s_film, BindingResult bindingResult) throws Exception{
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            mav.addObject("title","首页");
            mav.addObject("mainPage","film/indexFilm");
            mav.addObject("mainPageKey","#f");
            mav.setViewName("index");   //thymeleaf模板引擎默认返回的是.html文件
        }else{
            List<Film> filmList = filmService.list(s_film,1,32);
            mav.addObject("filmList",filmList);
            mav.addObject("title",s_film.getName());
            mav.addObject("s_name",s_film.getName());
            mav.addObject("mainPage","film/result");
            mav.addObject("mainPageKey","#f");
            mav.addObject("s_film",s_film);
            mav.addObject("total",filmList.size());
            mav.setViewName("index");   //thymeleaf模板引擎默认返回的是.html文件
        }
        return mav;
    }

    /**
     * 分页查询电影信息
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list/{id}")
    //@PathVariable获取路径中的变量
    public ModelAndView list(@PathVariable(value = "id",required = false)Integer page) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<Film> filmList = filmService.list(null,page,20);
        Long total = filmService.getCount(null);
        mav.addObject("title","电影列表");
        mav.addObject("mainPage","film/list");
        mav.addObject("mainPageKey","#f");
        mav.addObject("filmList",filmList);
        mav.addObject("pageCode", PageUtil.genPagination("/film/list",total,page,20));
        mav.setViewName("index");
        return mav;
    }

    /**
     * 获取电影详情信息
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}")
    public ModelAndView view(@PathVariable(value = "id",required = false)Integer id) throws Exception{
        ModelAndView mav = new ModelAndView();
        //获取实体类
        Film film = filmService.findById(id).get();
        mav.addObject("film",film);
        mav.addObject("randomFilmList",filmService.randomList(8));
        mav.addObject("webSiteInfoList",webSiteInfoService.getByFilmId(id));
        mav.addObject("title",film.getName());
        mav.addObject("pageCode",this.getUpAndDownPageCode(filmService.getNext(id),filmService.getLast(id)));
        mav.addObject("mainPage","film/view");
        mav.addObject("mainPageKey","#f");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 获取上下篇电影
     * @param nextfilm
     * @param lastfilm
     * @return
     */
    private String getUpAndDownPageCode(Film nextfilm,Film lastfilm){
        StringBuffer pageCode = new StringBuffer();
        if(lastfilm==null || lastfilm.getId()==null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else{
            pageCode.append("<p>上一篇：<a href='/film/"+lastfilm.getId()+"'>"+lastfilm.getTitle()+"</a></p>");
        }
        if(nextfilm==null || nextfilm.getId()==null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='/film/"+nextfilm.getId()+"'>"+nextfilm.getTitle()+"</a></p>");
        }
        return pageCode.toString();
    }
}
