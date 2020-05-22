package com.linka39.run;

import com.linka39.entity.Film;
import com.linka39.service.FilmService;
import com.linka39.service.LinkService;
import com.linka39.service.WebSiteInfoService;
import com.linka39.service.WebSiteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component("startupRunner")
public class StartupRunner implements CommandLineRunner , ServletContextListener {
    private ServletContext application = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    //CommandLineRunner注入过程，先依次实例化以下的接口，再实例化startupRunner
    //可以确保子接口未实例化时空指针报错
    @Resource
    private FilmService filmService;
    @Resource
    private WebSiteInfoService webSiteInfoService;
    @Resource
    private WebSiteService webSiteService;
    @Resource
    private LinkService linkService;

    @Override
    public void run(String... args) throws Exception {
        this.loadData();
    }

    /**
     * 加载数据到域中
     */
    public void loadData(){
        application.setAttribute("newestInfoList",webSiteInfoService.list(null,1,10));//最新10条电影动态
        Film film = new Film();
        film.setHot(1);
        application.setAttribute("newestHotFilmList",filmService.list(film,1,10));//最新10条热门电影
        application.setAttribute("newestIndexHotFilmList",filmService.list(film,1,32));//首页最新32条电影
        application.setAttribute("newestWebSiteList",webSiteService.newestList(1,10));//最新10条网址收录
        application.setAttribute("newestFilmList",filmService.list(null,1,10));//最新10条电影
        application.setAttribute("linkList",linkService.listAll());//最新10条电影
    }

}
