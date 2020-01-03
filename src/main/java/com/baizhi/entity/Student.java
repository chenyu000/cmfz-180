package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "生日", format = "yyyy-MM-dd")
    private Date bir;
    @Excel(name = "头像", type = 2, height = 20, width = 50)
    private String head_img;
    private String teacher_id;


}