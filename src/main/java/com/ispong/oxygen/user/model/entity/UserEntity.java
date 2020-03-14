package com.ispong.oxygen.user.model.entity;

import com.ispong.oxygen.common.BaseEntity;
import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.TableName;
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
@TableName("user_info")
public class UserEntity extends BaseEntity {

    @ColumnName("user_id")
    private String userId;

    @ColumnName("account")
    private String account;

    @ColumnName("password")
    private String password;

    @ColumnName("enabled_status")
    private String enabledStatus;
}
