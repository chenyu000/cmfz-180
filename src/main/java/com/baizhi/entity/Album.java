package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Serializable {

    private String id;
    private String title;
    private String img;
    private String score;
    private String author;
    private String broadcaster;
    private String count;
    private String brief;
    private Date createDate;
    private String status;
    private String other;

}