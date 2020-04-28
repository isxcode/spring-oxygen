package com.ispong.oxygen.module.user;

import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.flysql.common.BaseResponse;
import com.ispong.oxygen.module.log.Logs;
import com.ispong.oxygen.module.user.request.UserSignInReq;
import com.ispong.oxygen.module.user.request.UserSignUpReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Calendar;

/**
 * 用户模块
 *
 * @author ispong
 * @since 0.0.1
 */
@Logs
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    /**
     * 用户验证接口
     *
     * @param userSignInReq 用户登录请求对象
     * @since 0.0.1
     */
    @ApiOperation("用户登录接口")
    @PostMapping("/userSignIn")
    public ResponseEntity<BaseResponse<String>> userSignIn(@RequestBody UserSignInReq userSignInReq) {

        return successResponse("用户登录成功", userService.userSignIn(userSignInReq));
    }

    /**
     * 用户注册接口
     *
     * @param userSignUpReq 用户注册请求对象
     * @since 0.0.1
     */
    @ApiOperation("用户注册接口")
    @PostMapping("userSignUp")
    public ResponseEntity<BaseResponse<String>> userSignUp(@RequestBody UserSignUpReq userSignUpReq) {

        userService.userSignUp(userSignUpReq);
        return successResponse("用户注册成功", "");
    }

    /**
     * 测试项目启动接口
     *
     * @return 当前时间
     * @since 0.0.1
     */
    @GetMapping("/test")
    public ResponseEntity<BaseResponse<String>> test(@RequestParam("param") String param) {

        System.out.println("请求" + param);
        return successResponse("成功返回", Calendar.getInstance().getTime().toString());
    }

}
