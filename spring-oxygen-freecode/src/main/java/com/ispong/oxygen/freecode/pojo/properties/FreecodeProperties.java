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
package com.ispong.oxygen.freecode.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author ispong
 * @since 0.0.1
 */
@Data
@ConfigurationProperties("oxygen.freecode")
public class FreecodeProperties {

    /**
     * 文件类型
     */
    private List<String> fileTypes = Arrays.asList("controller", "entity", "service", "repository");

    /**
     * 忽略字段
     */
    private List<String> ignoreColumns;

    /**
     * 模块的地址
     */
    private String modulePath;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本号
     */
    private String version = "0.0.1";

    /**
     * 基础类Entity
     */
    private String baseEntityClass;

    /**
     * 基础类Controller
     */
    private String baseControllerClass;

    /**
     * 表名前缀
     */
    private String tablePrefix;
}
