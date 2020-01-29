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
package com.isxcode.oxygen.wechatgo.autoconfigure;

import com.isxcode.oxygen.core.constants.StarterConstants;
import com.isxcode.oxygen.wechatgo.model.WeChatAppInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性配置类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-28
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = StarterConstants.WECHATGO)
public class WechatgoProperties {

    /**
     * 临时储存token
     */
    public static final Map<String, String> WE_CHAT_ACCESS_TOKENS = new HashMap<>();

    /**
     * 所有的配置信息
     */
    private Map<String, WeChatAppInfo> apps;

    /**
     * 当前环境
     */
    private String env = "prod";

}
