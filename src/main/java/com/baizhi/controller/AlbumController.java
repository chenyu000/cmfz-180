package com.baizhi.controller;

import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("queryAlbumByPager")
    public Map<String, Object> queryAlbumByPager(Integer page, Integer rows) {
        Map<String, Object> map = albumService.queryByPager(page, rows);
        return map;
    }
}
