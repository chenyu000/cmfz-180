package com.baizhi.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDto implements Serializable {


    private Integer count;

    private Integer month;


}
