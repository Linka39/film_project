package com.linka39.service.impl;

import com.linka39.entity.Film;
import com.linka39.repository.FilmRepository;
import com.linka39.service.FilmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
