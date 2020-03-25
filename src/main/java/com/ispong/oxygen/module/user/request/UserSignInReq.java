package com.ispong.oxygen.module.user.request;

import lombok.Data;

@Data
public class UserSignInReq {

    private String account;

    private String password;
}
