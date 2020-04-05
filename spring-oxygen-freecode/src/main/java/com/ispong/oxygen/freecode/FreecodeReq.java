package com.ispong.oxygen.freecode;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FreecodeReq {

    /**
     * 数据源名称
     */
    @NotNull
    private String dataSourceName;

    /**
     * 表名
     */
    @NotNull
    private String tableName;

}
