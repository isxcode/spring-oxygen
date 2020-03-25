package com.ispong.oxygen.module.user;

import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.flysql.common.BaseResponse;
import com.ispong.oxygen.module.user.request.UserSignInReq;
import com.ispong.oxygen.module.user.request.UserSignUpReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块
 *
 * @author ispong
 * @since 0.0.1
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping
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

}
