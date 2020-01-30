package com.isxcode.oxygen.autocode;

import com.isxcode.oxygen.autocode.model.TableColumnInfo;
import com.isxcode.oxygen.autocode.model.TableInfo;
import com.isxcode.oxygen.flysql.FlySqlFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取数据库信息
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-09
 */
@Repository
public class CodeDao {

    /**
     * 通过表名和忽略字段获取表的字段数据
     *
     * @param tableName    表名
     * @param ignoreFields 忽略的字段
     * @return 返回字段信息
     * @since 2020-01-09
     */
    public List<TableColumnInfo> getTableColumns(String tableName, List<String> ignoreFields) {

        List<String> ignoreFieldList = new ArrayList<>(ignoreFields.size());
        ignoreFields.forEach(field -> ignoreFieldList.add("'" + field + "'"));
        String sqlStr = "show full columns from " + tableName + " where Field not in (" + String.join(",", ignoreFieldList) + ")";
        return FlySqlFactory.selectSql(TableColumnInfo.class).sql(sqlStr).query();
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
        return FlySqlFactory.selectSql(TableInfo.class).sql(sqlStr).getOne();
    }

}
