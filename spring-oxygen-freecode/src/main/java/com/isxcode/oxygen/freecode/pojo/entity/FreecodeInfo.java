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
package com.isxcode.oxygen.freecode.pojo.entity;

import com.isxcode.oxygen.freecode.pojo.properties.FreecodeProperties;
import lombok.Data;

import java.util.List;

/**
 * freemarker 属性对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class FreecodeInfo {

    /**
     * 表字段信息
     */
    private List<TableColumnInfo> tableColumns;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 文件名
     */
    private String className;

    /**
     * 原表名
     */
    private String primaryTableName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模型的package class
     */
    private List<String> entityPackageList;

    /**
     * 基础属性
     */
    private FreecodeProperties freecodeProperties;
}
