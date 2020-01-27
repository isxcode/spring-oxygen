package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.common.BaseController;
import com.isxcode.oxygen.common.BaseResponse;
import com.isxcode.oxygen.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试jpa的基础操作
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-20
 */
@RestController
@RequestMapping("/sql")
public class SqlController extends BaseController {

    private final SqlService sqlService;

    public SqlController(SqlService sqlService) {

        this.sqlService = sqlService;
    }

    @GetMapping("get")
    public ResponseEntity<BaseResponse> getOne() {

        // 我想做成什么样子 工厂模式生成builder
        FlySqlFactory.selectSql(UserDto.class).getOne();


        return successResponse("查询成功","");
    }

}
