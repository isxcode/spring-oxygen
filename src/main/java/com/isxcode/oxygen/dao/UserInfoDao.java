package com.isxcode.oxygen.dao;

import com.isxcode.oxygen.flysql.FlySqlFactory;
import com.isxcode.oxygen.model.entity.UserInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 用户表 Dao
*
* @author ispong
* @since 2020-01-13T14:43:46.059854200
*/
@Slf4j
@Repository
public class UserInfoDao {

    /**
     * 查询用户表
     *
     * @return 返回用户表
     * @since 2020-01-13
     */
    public List<UserInfoEntity> queryUserInfo() {

        return FlySqlFactory.selectSql(UserInfoEntity.class).query();
    }

}
