package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget(value = "老师与学生们")
public class Teacher {
    @Excel(name = "编号", needMerge = true)
    private String id;
    @Excel(name = "教师姓名", needMerge = true)
    private String name;
    @Excel(name = "科目", needMerge = true)
    private String project;
    @ExcelCollection(name = "学生们")
    private List<Student> list;
}
