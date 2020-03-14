package com.ispong.oxygen.user.controller;

import com.ispong.oxygen.user.dao.UserDao;
import com.ispong.oxygen.user.model.entity.UserEntity;
import com.ispong.oxygen.user.model.entity.UserViewEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {

        this.userDao = userDao;
    }

    @GetMapping("/queryUser")
    public List<UserEntity> queryUserEntity() {

        return userDao.queryUser();
    }

    @GetMapping("/queryViewUser")
    public List<UserViewEntity> queryViewUser() {

        return userDao.queryViewUser();
    }

    @GetMapping("/saveUser")
    public void saveUserEntity() {

        userDao.insertUser();
    }

    @GetMapping("/updateUser")
    public void updateUserEntity() {

        userDao.updateUser();
    }

    @GetMapping("/deleteUser")
    public void deleteUserEntity() {

        userDao.deleteUser();
    }

    @GetMapping("/countUser")
    public String countUserEntity() {

        return String.valueOf(userDao.countUser());
    }
}
