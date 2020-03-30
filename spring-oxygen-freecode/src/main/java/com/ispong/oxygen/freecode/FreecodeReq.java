package com.ispong.oxygen.freecode;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FreecodeReq {


    @NotNull(message = "tableName 不能为空")
    private String tableName;
}
