package com.baizhi.service;

import java.util.Map;

public interface AlbumService {
    //分页查询
    Map<String, Object> queryByPager(Integer page, Integer rows);
}
