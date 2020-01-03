package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 *
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    @JsonIgnore
    private String id;
    @JsonProperty("name")
    private String username;
    private String password;
}
