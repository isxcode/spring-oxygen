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
package com.isxcode.oxygen.wechatgo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * init wechat token
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class WeChatTokenGenerator implements InitializingBean {

    private final WechatgoService weChatService;

    public WeChatTokenGenerator(WechatgoService weChatService) {

        this.weChatService = weChatService;
    }

    @Override
    public void afterPropertiesSet() {

        ScheduledExecutorService executorService = newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new WechatgoTokenThread(weChatService), 0, 90, TimeUnit.MINUTES);
    }
}
