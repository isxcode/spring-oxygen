package com.isxcode.ispring.utils.sql;


import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Integer page;

    private Integer size;

    private Integer total;

    private List<T> pageList;
}
