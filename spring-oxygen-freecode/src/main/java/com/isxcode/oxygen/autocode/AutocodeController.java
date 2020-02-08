/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isxcode.oxygen.autocode;

import com.isxcode.oxygen.flysql.common.BaseController;
import com.isxcode.oxygen.flysql.common.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供api接口创建代码
 *
 * @author ispong
 * @version v0.1.0
 */
@RestController
public class AutocodeController extends BaseController {

    private final CodeService codeService;

    public AutocodeController(CodeService codeService) {

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

        Assert.isTrue(!StringUtils.isEmpty(codeDto.getTableName()), "tableName 不能为空");

        codeService.generateCode(codeDto.getTableName());

        return successResponse("自动代码生成成功", "");
    }

}
