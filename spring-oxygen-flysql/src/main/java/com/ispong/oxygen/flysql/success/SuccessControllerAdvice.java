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
package com.ispong.oxygen.flysql.success;

import com.ispong.oxygen.flysql.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常捕获
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SuccessControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SuccessException.class)
    public ResponseEntity<BaseResponse<Object>> successException(SuccessException successException) {

        return new ResponseEntity<>(successException.getBaseResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Object>> assertException(IllegalArgumentException illegalArgumentException) {

        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setCode("500");
        baseResponse.setMsg(illegalArgumentException.getMessage());
        baseResponse.setData("");
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }
}
