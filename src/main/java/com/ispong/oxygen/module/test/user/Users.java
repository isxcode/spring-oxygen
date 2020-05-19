package com.ispong.oxygen.module.test.user;

import com.ispong.oxygen.flysql.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("USERS")
public class Users {

    private Date birthday;

    private Integer objectVersionNumber;

    private String createdBy;

    private Date creationDate;

    private String lastUpdatedBy;

    private Date lastUpdateDate;

    private String userId;

    private String orgId;

    private String uname;

    private String uemail;

    private String uAccountId;

    private String sex;

    private String phone;

    private String password;

    private String status;

    private String avatarAtchId;

    private String sysRole;

    private String uId;

    private String deptCode;
}
