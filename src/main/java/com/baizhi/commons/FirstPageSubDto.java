package com.baizhi.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * {
 * "thumbnail": "http://wenwen.soso.com/p/20111105/20111105224324-483943250.jpg",
 * "title": "莲师传",	//标题
 * "author": "",		//描述
 * "type": "0",	    //类型（0：闻，1：思）
 * "set_count": "30",	//集数（只有闻的数据才有）
 * "create_date": "2015.03.1"	//创建时间
 * },
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstPageSubDto implements Serializable {

    private String thumbnail;

    private String title;

    private String author;

    private String type;

    private String set_count;

    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date create_date;


}
