package com.baizhi.dao;

import com.baizhi.entity.Teacher;

import java.util.List;

public interface TeacherMapper {
    /*
     *  查询所有老师
     * */
    List<Teacher> queryAll();
}
