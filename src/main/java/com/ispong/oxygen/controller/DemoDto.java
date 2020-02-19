package com.ispong.oxygen.controller;

import com.ispong.oxygen.flysql.annotation.DateBaseType;
import com.ispong.oxygen.flysql.annotation.FlysqlView;
import lombok.Data;

@FlysqlView(type = DateBaseType.MYSQL, value = "select account,password password,enabled_status from user_info where password=:password")
@FlysqlView(type = DateBaseType.ORACLE, value = "select * from user_info")
@Data
public class DemoDto {

    private String account;

    private String password;

    private String enabledStatus;
}
