package com.ispong.oxygen.service;

import com.ispong.oxygen.dao.UserInfoDao;
import com.ispong.oxygen.model.entity.UserInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表 Service
 *
 * @author ispong
 * @since 2020-01-13T14:43:45.977075500
 */
@Slf4j
@Service
public class UserInfoService {

    private final UserInfoDao userInfoDao;

    public UserInfoService(UserInfoDao userInfoDao) {

        this.userInfoDao = userInfoDao;
    }

    /**
     * 查询用户表
     *
     * @return 返回用户表
     * @since 2020-01-13
     */
    public List<UserInfoEntity> queryUserInfo() {

        return userInfoDao.queryUserInfo();
    }

}
