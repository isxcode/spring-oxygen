package com.ispong.oxygen.module.user.view;

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.FlysqlView;
import lombok.Data;

@FlysqlView("select * from user_info")
@FlysqlView("hhh")
@Data
public class UserViewEntity {

    @ColumnName("account")
    private String name1;

    @ColumnName("password")
    private String name2;

    @ColumnName("enabled_status")
    private String name3;
}
