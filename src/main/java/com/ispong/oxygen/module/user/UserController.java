package com.ispong.oxygen.module.user;

import com.ispong.oxygen.core.encrypt.EncryptUtils;
import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.module.user.entity.UserEntity;
import com.ispong.oxygen.module.user.view.UserViewEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController
public class UserController extends BaseController {

    private final UserRepository userRepository;

    @GetMapping("/userAuth")
    public String userLogin() {

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("ispong");
        return EncryptUtils.jwtEncrypt(userEntity);
    }

    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @PostMapping("/queryUser")
    public List<UserEntity> queryUserEntity() {

        return userRepository.queryUser();
    }

    @GetMapping("/queryViewUser")
    public List<UserViewEntity> queryViewUser() {

        return userRepository.queryViewUser();
    }

    @GetMapping("/saveUser")
    public void saveUserEntity() {

        userRepository.insertUser();
    }

    @GetMapping("/updateUser")
    public void updateUserEntity() {

        userRepository.updateUser();
    }

    @GetMapping("/deleteUser")
    public void deleteUserEntity() {

        userRepository.deleteUser();
    }

    @GetMapping("/countUser")
    public String countUserEntity() {

        return String.valueOf(userRepository.countUser());
    }

    @PostMapping("/postAdmin")
    public Object getStr(@RequestBody String name) {

        // 请求数据 做数据校验

        // 返回数据 做数据整合

        // 不想封装对象,创建对象很烦


        return "service.get();";
    }
}
