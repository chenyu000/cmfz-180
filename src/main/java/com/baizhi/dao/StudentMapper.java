package com.baizhi.dao;

import com.baizhi.entity.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> queryAll();

    //批量插入
    void insertAllStudents(List<Student> list);
}