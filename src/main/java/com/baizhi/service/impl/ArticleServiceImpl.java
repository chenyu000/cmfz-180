package com.baizhi.service.impl;

import com.baizhi.dao.ArticleMapper;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Map<String, Object> queryByPager(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        List<Article> list = articleMapper.queryByPager(start, rows);
        Integer count = articleMapper.queryCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("records", count);
        map.put("total", total);
        return map;
    }
}
