package com.isxcode.ispring.controller;

import com.isxcode.ispring.common.BaseController;
import com.isxcode.ispring.common.BaseResponse;
import com.isxcode.ispring.model.dto.UserDto;
//import com.isxcode.ispring.security.UserSecurityDetail;
import com.isxcode.ispring.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

/**
 * spring项目测试类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-11
 */
@Slf4j
@Controller
public class HelloController extends BaseController {

    /**
     * 测试项目启动
     *
     * @since 2019-11-15
     */
    @GetMapping("/test")
    public ResponseEntity<BaseResponse> test() {

        return successResponse("项目启动成功", Calendar.getInstance().getTime().toString());
    }

//    /**
//     * 用户登录界面
//     *
//     * @since 2019-12-13
//     */
//    @RequestMapping("/login")
//    public String login() {
//
//        return "login";
//    }

//    /**
//     * 支持oauth和普通登录
//     *
//     * @since 2019-12-13
//     */
//    @PostMapping("/userAuth")
//    public ResponseEntity<BaseResponse> userAuth(@RequestBody UserSecurityDetail userSecurityDetail) {
//
//
//
//        return successResponse("登录成功", JwtUtils.encryptJwt(userInfo));
//    }



}

