package com.ispong.oxygen.controller;

import com.ispong.oxygen.common.BaseController;
import com.ispong.oxygen.common.BaseResponse;
import com.ispong.oxygen.flysql.FlySqlFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

//import com.isxcode.ispring.security.UserSecurityDetail;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;

/**
 * spring项目测试类
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
@RestController
public class HelloController extends BaseController {

    /**
     * 测试项目启动
     *
     * @since 2019-11-15
     */
    @GetMapping("/test3")
    public ResponseEntity<BaseResponse> test() {

//                 .eq("created_by", "system")
//                .lt("created_date", LocalDateTime.now())
//                .like("password", "ispong%")
//                .in("password", "ispong1", "ispong2")
//                .orderBy("created_date", "desc")
//                .orderBy("created_by", "asc")
        return successResponse("项目启动成功", Calendar.getInstance().getTime().toString());
    }

    /**
     * 用户登录界面
     *
     * @since 2019-12-13
     */
    @RequestMapping("/login")
    public String login() throws InvocationTargetException, IllegalAccessException {

//        List<DemoDto> query = FlySqlFactory.selectSql(DemoDto.class)
//                .select("account1","password1","enabledStatus1")
//                .eq("enabledStatus1", "enable")
//                .query();

//        return Strings.join(query, ',');

        DemoDto demoDto = new DemoDto();
        demoDto.setAccount1("hhh");
        demoDto.setEnabledStatus1("ena");
        demoDto.setPassword1("123");
        demoDto.setUuid("123");
        FlySqlFactory.insertSql(DemoDto.class).save(demoDto);
        return "";
    }

    /**
     * 用户登录界面
     *
     * @since 2019-12-13
     */
    @RequestMapping("/login2")
    public String login2() {

        FlySqlFactory.updateSql(DemoDto.class)
                .eq("password","ispong2")
                .update("account","ispong123")
                .doUpdate();

        return null;
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

