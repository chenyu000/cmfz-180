package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {
    int deleteByPrimaryKey(String id);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);

    //分页查询
    List<Album> queryByPager(@Param("start") Integer start, @Param("rows") Integer rows);

    //查询总条数
    Integer queryAlbumCount();
}