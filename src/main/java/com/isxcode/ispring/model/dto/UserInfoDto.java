package com.isxcode.ispring.model.dto;

import com.isxcode.ispring.common.BaseEntity;
import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;

@Data
//@Value
public class UserInfoDto{

    private String userId;

    private String password;
}
