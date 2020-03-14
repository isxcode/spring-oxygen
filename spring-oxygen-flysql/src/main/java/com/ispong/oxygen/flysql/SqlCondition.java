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
package com.ispong.oxygen.flysql;

import com.ispong.oxygen.flysql.enums.SqlOperateType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * sql条件对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
public class SqlCondition {

    /**
     * 条件类型
     */
    private SqlOperateType operateType;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段内容
     */
    private Object value;
}
