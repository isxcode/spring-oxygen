package com.ispong.oxygen.freecode;

import com.ispong.oxygen.flysql.Flysql;
import com.ispong.oxygen.freecode.model.TableColumnInfo;
import com.ispong.oxygen.freecode.model.TableInfo;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreecodeRepository {

    private final Map<String, DataSource> dataSourceMap;

    public FreecodeRepository(Map<String, DataSource> dataSourceMap) {

        this.dataSourceMap = dataSourceMap;
    }

    /**
     * 通过表名和忽略字段获取表的字段数据
     *
     * @param tableName    表名
     * @param ignoreFields 忽略的字段
     * @return 返回字段信息
     * @since 2020-01-09
     */
    public List<TableColumnInfo> getTableColumns(String dataSourceName, String tableName, List<String> ignoreFields) {

        DataSource dataSource = dataSourceMap.get(dataSourceName);

        String sqlStr = "show full columns from " + tableName;
        if (ignoreFields != null && !ignoreFields.isEmpty()) {
            List<String> ignoreFieldList = new ArrayList<>(ignoreFields.size());
            ignoreFields.forEach(field -> ignoreFieldList.add("'" + field + "'"));
            sqlStr = sqlStr + " where Field not in (" + String.join(",", ignoreFieldList) + ")";
        }
        return Flysql.select(TableColumnInfo.class).sql(sqlStr).query();
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
