package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    int deleteByPrimaryKeys(String[] id);

    int insert(Chapter record);

    int insertSelective(Chapter chapter);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);

    List<Chapter> selectByPager(@Param("start") Integer start, @Param("rows") Integer rows, @Param("albumId") String albumId);

    Integer queryCount(String albumId);
}