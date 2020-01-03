package com.baizhi.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstPageDto implements Serializable {

    private List<Map<String, String>> header = new ArrayList<>();

    private List<FirstPageSubDto> body = new ArrayList<>();

}
