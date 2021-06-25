package com.isxcode.oxygen.flysql.entity;

import lombok.Data;

import java.util.List;

@Data
public class FlysqlPage<T>{

    private List<T> page;

    private Integer total;
}
