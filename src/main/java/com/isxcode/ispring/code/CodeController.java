package com.isxcode.ispring.code;

import com.isxcode.ispring.common.BaseController;
import com.isxcode.ispring.common.BaseResponse;
import com.isxcode.ispring.utils.FormatUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码自动生成
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-08
 */
@RestController
public class CodeController extends BaseController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {

        this.codeService = codeService;
    }

    /**
     * 通过表名自动生成controller/service/dao/entity文件
     *
     * @param codeDto 对象请求
     * @since 2020-01-08
     */
    @PostMapping("generateCode")
    public ResponseEntity<BaseResponse> generateCode(@RequestBody CodeDto codeDto) {

        FormatUtils.checkEmptyStr(codeDto.getTableName(), "tableName 不能为空");

        codeService.generateCode(codeDto.getTableName());

        return successResponse("自动代码生成成功", "");
    }


}
