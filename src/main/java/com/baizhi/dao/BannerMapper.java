package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    int deleteByPrimaryKeys(String[] id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    // 分页查询
    List<Banner> queryByPager(@Param("start") Integer start, @Param("rows") Integer rows);

    //查询总条数
    Integer queryRecords();
}