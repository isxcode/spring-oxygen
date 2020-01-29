package com.isxcode.oxygen.controller;

import com.isxcode.oxygen.autocode.CodeDto;
import com.isxcode.oxygen.common.BaseController;
import com.isxcode.oxygen.common.BaseResponse;
//import com.isxcode.ispring.security.UserSecurityDetail;
import com.isxcode.oxygen.model.dto.UserDto;
import com.isxcode.oxygen.core.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/test3")
    public ResponseEntity<BaseResponse> test() {

        return successResponse("项目启动成功", Calendar.getInstance().getTime().toString());
    }

    /**
     * 用户登录界面
     *
     * @since 2019-12-13
     */
    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    /**
     * 用户登录接口
     *
     * @since 2019-12-13
     */
    @PostMapping("/userAuth")
    public ResponseEntity<BaseResponse> userAuth(@RequestBody CodeDto codeDto) {

        // 数据库表字段信息
        // 获取数据库字段信息

        // 获取数据库信息  包括名称属性 备注

        // 遍历template包  结合作者信息 和 数据库封装的信息

        // 指定生成文件

//        LoggerFactory.getLogger()
//        log.info();
        UserDto userDto = new UserDto();
        userDto.setUsername("123");
        userDto.setPassword("123");
        return successResponse("登录成功", JwtUtils.encryptJwt(userDto));

    }

    /**
     * 测试权限接口
     *
     * @since 2019-12-13
     */
//    @Secured({"ROLE_USER"})
    @PostMapping("/test1")
    public ResponseEntity<BaseResponse> test1() {

        return successResponse("test1成功", "");
    }

    /**
     * 测试权限接口
     *
     * @since 2019-12-13
     */
//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/test2")
    public ResponseEntity<BaseResponse> test2() {

        return successResponse("test2成功", "");
    }

}

