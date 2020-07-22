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
package com.ispong.oxygen.flysql.utils;

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.TableName;
import com.ispong.oxygen.flysql.pojo.constant.FlysqlConstants;
import com.ispong.oxygen.flysql.pojo.entity.SqlCondition;
import com.ispong.oxygen.flysql.pojo.enums.SqlOperateType;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

/**
 * rowId 自动生成id工具类
 *
 * @author ispong
 * @since 0.0.1
 */
public class IdGenerateUtils {

    /**
     * 普通uuid生成器
     *
     * @since 2019-12-24
     */
    private static String uuidGenerator() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 雪花算法id生成器
     *
     * @since 2019-12-24
     */
//    private static String snowFlakeGenerator() {
//
//
//    }
}
