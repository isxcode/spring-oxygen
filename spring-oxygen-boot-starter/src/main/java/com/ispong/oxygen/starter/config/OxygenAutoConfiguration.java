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
package com.ispong.oxygen.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * 自动配置
 *
 * @author ispong
 * @since 0.0.1
 */
public class OxygenAutoConfiguration {

    @Bean
    @ConditionalOnClass(OxygenAutoConfiguration.class)
    private void initOxygenBanner() {

        System.out.println("   _____            _                   ____                            ");
        System.out.println("  / ___/____  _____(_)___  ____ _      / __ \\_  ____  ______ ____  ____ ");
        System.out.println("  \\__ \\/ __ \\/ ___/ / __ \\/ __ `/_____/ / / / |/_/ / / / __ `/ _ \\/ __ \\");
        System.out.println(" ___/ / /_/ / /  / / / / / /_/ /_____/ /_/ />  </ /_/ / /_/ /  __/ / / /");
        System.out.println("/____/ .___/_/  /_/_/ /_/\\__, /      \\____/_/|_|\\__, /\\__, /\\___/_/ /_/ ");
        System.out.println("    /_/                 /____/                 /____//____/             ");
    }

}
