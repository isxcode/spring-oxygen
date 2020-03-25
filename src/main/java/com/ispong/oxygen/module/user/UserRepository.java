package com.ispong.oxygen.module.user;

import com.ispong.oxygen.flysql.Flysql;
import com.ispong.oxygen.module.user.entity.UserEntity;
import com.ispong.oxygen.module.user.view.UserViewEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
public class UserRepository {

    public List<UserEntity> queryUser() {

        return Flysql.select(UserEntity.class,"ispong")
                .query();
    }

    public List<UserViewEntity> queryViewUser() {

        return Flysql.view(UserViewEntity.class, "ispong")
                .query();
    }

    public void insertUser(){

        UserEntity userEntity = new UserEntity();
        userEntity.setAccount("ispong");
        userEntity.setPassword("123456");
        userEntity.setEnabledStatus("enable");
        userEntity.setUserId("ispong");
        userEntity.setCreatedBy("system");
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setLastModifiedBy("system");
        userEntity.setLastModifiedDate(LocalDateTime.now());
        userEntity.setUserId("1234");
        Flysql.insert(UserEntity.class,"ispong").save(userEntity);

    }

    public void updateUser(){

        Flysql.update(UserEntity.class, "ispong")
                .update("account","平松")
                .eq("createdBy", "system")
                .doUpdate();

    }

    public void deleteUser(){

        Flysql.delete(UserEntity.class, "ispong")
                .eq("createdBy", "system")
                .doDelete();

    }

    public Integer countUser(){

        return Flysql.select(UserEntity.class, "ispong")
                .eq("createdBy", "system")
                .count();
    }

}
