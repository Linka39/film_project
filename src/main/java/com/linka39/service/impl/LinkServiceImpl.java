package com.linka39.service.impl;

import com.linka39.entity.Link;
import com.linka39.repository.LinkRepository;
import com.linka39.service.LinkService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友情连接ervice接口实现类
 */
@Service("linkService")//实现业务层逻辑 bean类的装填
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkRepository linkRepository;
    @Override
    public List<Link> list(Integer page, Integer pageSize) {
        //PageRequest不需要 new ，内部调用构造器
        return linkRepository.findAll(PageRequest.of(page,pageSize)).getContent();//内部已经封装的分页方法
    }

    @Override
    public Long getCount() {
        return linkRepository.count();
    }
}
