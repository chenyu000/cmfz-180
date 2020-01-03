package com.baizhi.service;

import java.util.Map;

public interface ArticleService {
    Map<String, Object> queryByPager(Integer page, Integer rows);
}
