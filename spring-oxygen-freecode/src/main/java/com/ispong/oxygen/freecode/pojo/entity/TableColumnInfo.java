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
package com.ispong.oxygen.freecode.pojo.entity;

import com.ispong.oxygen.freecode.utils.FreecodeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库表每个字段的所有信息对象
 *
 * @author ispong
 * @version v0.1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableColumnInfo {

    /**
     * 字段名
     */
    private String field;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字符集
     */
    private String collation;

    /**
     * 主键
     */
    private String key;

    /**
     * 额外值
     */
    private String extra;

    /**
     * 权限
     */
    private String privileges;

    /**
     * 字段备注
     */
    private String comment;

    private String originField;

    public String getType() {

        return FreecodeUtils.parseDataType(type);
    }

    public String getField() {

        return FreecodeUtils.lineToHump(field);
    }

    public String getOriginField() {
        return this.field;
    }
}
