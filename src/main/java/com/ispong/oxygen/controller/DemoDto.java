package com.ispong.oxygen.controller;

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.model.enums.DateBaseType;
import com.ispong.oxygen.flysql.annotation.FlysqlView;
import com.ispong.oxygen.flysql.annotation.TableName;
import lombok.Data;

@FlysqlView(type = DateBaseType.MYSQL, value = "select account,password password,created_date,enabled_status from user_info where password=:password")
@FlysqlView(type = DateBaseType.ORACLE, value = "select * from user_info")
@Data
@TableName("user_info")
public class DemoDto {

    private String uuid;

    @ColumnName("account")
    private String account1;

    @ColumnName("password")
    private String password1;

    @ColumnName("enabled_status")
    private String enabledStatus1;
}
