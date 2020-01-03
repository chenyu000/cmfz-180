package com.baizhi.service.impl;

import com.baizhi.dao.AlbumMapper;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;


    @Override
    public Map<String, Object> queryByPager(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        List<Album> list = albumMapper.queryByPager(start, rows);
        Integer records = albumMapper.queryAlbumCount();
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("records", records);
        map.put("total", total);
        return map;
    }
}
