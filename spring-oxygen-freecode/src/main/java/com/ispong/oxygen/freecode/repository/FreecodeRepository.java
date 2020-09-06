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
package com.ispong.oxygen.freecode.repository;

import com.ispong.oxygen.flysql.core.Flysql;
import com.ispong.oxygen.freecode.exception.FreecodeException;
import com.ispong.oxygen.freecode.pojo.entity.TableColumnInfo;
import com.ispong.oxygen.freecode.pojo.entity.TableInfo;
import com.ispong.oxygen.freecode.utils.FreecodeUtils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库交互层
 *
 * @author ispong
 * @since 0.0.1
 */
public class FreecodeRepository {

    /**
     * 通过表名和忽略字段获取表的字段数据
     *
     * @param tableName    表名
     * @param ignoreFields 忽略的字段
     * @return 返回字段信息
     * @since 2020-01-09
     */
    @SneakyThrows
    public List<TableColumnInfo> getTableColumns(String tableName, List<String> ignoreFields) {

        // 区分数据库类型
        String databaseType = Flysql.getDataSource().getConnection().getCatalog();

        String sqlStr;
        switch (databaseType) {
            case "H2":
                sqlStr = "show columns from " + tableName;
                break;
            case "MYSQL":
                sqlStr = "show full columns from " + tableName;
                break;
            default:
                throw new FreecodeException("dataSource type not support");
        }

        List<TableColumnInfo> tableColumnInfos = Flysql.select(TableColumnInfo.class).sql(sqlStr).query();

        // 忽略字段
        List<TableColumnInfo> tempTableColumnInfos = new ArrayList<>();
        if (ignoreFields != null) {
            main:
            for (TableColumnInfo metaColumnInfo : tableColumnInfos) {
                for (String metaIgnoreField : ignoreFields) {
                    if (metaColumnInfo.getField().equals(FreecodeUtils.lineToHump(metaIgnoreField))) {
                        break main;
                    }
                }
                tempTableColumnInfos.add(metaColumnInfo);
            }
            return tempTableColumnInfos;
        } else {

            return tableColumnInfos;
        }

    }

    /**
     * 通过表名获取数据库表信息
     *
     * @param tableName 表名
     * @return 返回字段信息
     * @since 2020-01-09
     */
    public TableInfo getTableInfo(String tableName) {

        String sqlStr = "select TABLE_COMMENT from information_schema.TABLES where TABLE_NAME = '" + tableName + "'";
        return Flysql.select(TableInfo.class).sql(sqlStr).getOne();
    }
}
