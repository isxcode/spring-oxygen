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
 *
 */
package com.isxcode.oxygen.wechatgo.model;

import lombok.Data;

/**
 * wechat access_token
 *
 * @author ispong
 * @version v0.1.0
 */
@Data
public class WeChatAccessToken {

    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private Integer expires_in;

    /**
     * 返回错误码
     */
    private Integer errcode = 0;

    /**
     * 错误信息
     */
    private String errmsg;
}
