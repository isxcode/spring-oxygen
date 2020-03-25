package com.ispong.oxygen.module.user.entity;

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.TableName;
import com.ispong.oxygen.flysql.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户数据模块信息
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserEntity extends BaseEntity {

    @ColumnName("user_uuid")
    private String userId;

    @ColumnName("account")
    private String account;

    @ColumnName("password")
    private String password;

    @ColumnName("authority")
    private String authority;

    @ColumnName("phone")
    private String phone;
}
