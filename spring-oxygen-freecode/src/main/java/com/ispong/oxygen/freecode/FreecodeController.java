package com.ispong.oxygen.freecode;

import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.flysql.common.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping
public class FreecodeController extends BaseController {

    private final FreecodeService freecodeService;

    public FreecodeController(FreecodeService freecodeService) {

        this.freecodeService = freecodeService;
    }

    @PostMapping("freecode")
    public ResponseEntity<BaseResponse<String>> generateCode(@Valid @RequestBody FreecodeReq freecodeReq) {

//        freecodeService

        return successResponse("自动代码生成成功", "");
    }
}
