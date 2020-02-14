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

import com.fasterxml.jackson.annotation.JsonSetter;
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
     * access_token
     */
    @JsonSetter("access_token")
    private String accessToken;

    /**
     * expires_in(sec)
     */
    @JsonSetter("expires_in")
    private Integer expiresIn;

    /**
     * err code
     */
    @JsonSetter("errcode")
    private Integer errCode = 0;

    /**
     * err msg
     */
    @JsonSetter("errMsg")
    private String errMsg;
}
