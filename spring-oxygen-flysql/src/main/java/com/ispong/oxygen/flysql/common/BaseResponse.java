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
package com.ispong.oxygen.flysql.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口返回模板
 * BaseResponse
 *
 * @author ispong
 * @version v0.1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回的msg
     */
    private String msg;

    /**
     * 返回体数据
     */
    private T data;
}
