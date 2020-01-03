package com.baizhi.dao;

import com.baizhi.commons.MapDto;

import java.util.List;

public interface UserMapper {

    List<MapDto> selectMonthAllByRegister();
}