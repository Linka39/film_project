package com.linka39.controller;

import com.linka39.entity.Film;
import com.linka39.entity.Link;
import com.linka39.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 电影搜索Comtroller类
 */
@Controller  //一定要加上注解为Controller
@RequestMapping("/film")
public class FilmController {
    @Resource
    FilmService filmService;

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

}
