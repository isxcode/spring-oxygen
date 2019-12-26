package com.isxcode.ispring.utils.sql;

import com.isxcode.ispring.common.BaseController;
import com.isxcode.ispring.common.BaseResponse;
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

        for (int i = 0; i < 19; i++) {
            long tempSecond = System.currentTimeMillis();
            sqlService.getOne();
            System.out.printf("使用时间" + (System.currentTimeMillis() - tempSecond));
            System.out.printf("");
        }
        return successResponse("查询成功","");
    }

}
