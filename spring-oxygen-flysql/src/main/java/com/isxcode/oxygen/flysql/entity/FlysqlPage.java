package com.isxcode.oxygen.flysql.entity;

import java.util.List;
import lombok.Data;

@Data
public class FlysqlPage<T> {

	private List<T> page;

	private Integer total;
}
