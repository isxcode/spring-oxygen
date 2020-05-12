package com.ispong.oxygen.module.user;

import com.ispong.oxygen.utils.encrypt.EncryptUtils;
import com.ispong.oxygen.exception.AuthException;
import com.ispong.oxygen.module.user.entity.UserEntity;
import com.ispong.oxygen.module.user.request.UserSignInReq;
import com.ispong.oxygen.module.user.request.UserSignUpReq;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserEntity getUserInfo(String userId) {

        return userRepository.getUserInfo(userId);
    }

    public void userSignUp(UserSignUpReq userSignUpReq) {

        UserEntity userEntity = new UserEntity();
        userEntity.setAccount(userSignUpReq.getAccount());
        userEntity.setAuthority("admin");
        userEntity.setPassword(userSignUpReq.getPassword());
        userEntity.setPhone(userSignUpReq.getPhone());
        userEntity.setUserId(UUID.randomUUID().toString());
        userRepository.saveUser(userEntity);
    }

    public String userSignIn(UserSignInReq userSignInReq) {

        UserEntity userEntity = userRepository.getUserInfoByAccount(userSignInReq.getAccount());

        if (userEntity == null) {
            throw new AuthException("用户不存在");
        }

        if (!userEntity.getPassword().equals(userSignInReq.getPassword())) {
            throw new AuthException("密码错误");
        }

        return EncryptUtils.jwtEncrypt(userEntity);

    }


}
