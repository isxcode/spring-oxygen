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
package com.isxcode.oxygen.flysql.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * BaseController
 *
 * @author ispong
 * @version v0.1.0
 */
public abstract class BaseController {

    /**
     * 成功返回
     *
     * @param message message
     * @param data    返回数据体
     * @return 返回BaseResponse
     * @since 2019/10/16
     */
    protected ResponseEntity<BaseResponse> successResponse(String message, Object data) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(String.valueOf(HttpStatus.OK.value()));
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}