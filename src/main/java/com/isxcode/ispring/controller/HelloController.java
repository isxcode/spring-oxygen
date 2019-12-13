package com.isxcode.ispring.controller;

import com.isxcode.ispring.common.BaseController;
import com.isxcode.ispring.common.BaseResponse;
import com.isxcode.ispring.model.dto.UserDto;
import com.isxcode.ispring.security.UserSecurityDetail;
import com.isxcode.ispring.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

/**
 * spring项目测试类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-11
 */
@Slf4j
@RestController
@RequestMapping("hello")
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

    /**
     * 用户登录
     *
     * @param userDto 用户请求DTO
     * @since 2019-12-13
     */
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody UserDto userDto) {

        if(userDto.getPassword().equals("123")){
            UserSecurityDetail userSecurityDetail = new UserSecurityDetail();
            userSecurityDetail.setUsername(userDto.getUsername());
            userSecurityDetail.setPassword(userDto.getPassword());
            return successResponse("登录成功", JwtUtils.encryptJwt(userSecurityDetail));
        }else{
            return successResponse("登录失败", "");
        }

    }

    /**
     * 用户登录页面
     *
     * @since 2019-12-13
     */
    @PostMapping("/loginPage")
    public ModelAndView loginPage() {


        return new ModelAndView("");
    }


    /**
     * 获取用户信息
     *
     * @since 2019-12-13
     */
    @PostMapping("/getUser")
    public ResponseEntity<BaseResponse> getUser() {

        return successResponse("获取用户信息", getUserInfo());
    }
}

