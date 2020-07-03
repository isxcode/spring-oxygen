package com.ispong.oxygen.freecode.repository;

import com.ispong.oxygen.freecode.exception.FreecodeException;
import com.ispong.oxygen.freecode.pojo.entity.TableColumnInfo;
import com.ispong.oxygen.freecode.pojo.entity.TableInfo;
import com.ispong.oxygen.freecode.utils.FreecodeUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class FreecodeRepository {

    private final JdbcTemplate jdbcTemplate;

    public FreecodeRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 通过表名和忽略字段获取表的字段数据
     *
     * @param tableName      表名
     * @param ignoreFields   忽略的字段
     * @return 返回字段信息
     * @since 2020-01-09
     */
    public List<TableColumnInfo> getTableColumns(String tableName, List<String> ignoreFields) {

        // 区分数据库类型
        HikariDataSource dataSource = (HikariDataSource) jdbcTemplate.getDataSource();
        if (dataSource != null) {
            String sqlStr;
            switch (dataSource.getDriverClassName()) {
                case "org.h2.Driver":
                    // h2
                    sqlStr = "show columns from " + tableName;
                    break;
                case "com.mysql.cj.jdbc.Driver":
                    // mysql
                    sqlStr = "show full columns from " + tableName;
                    break;
                default:
                    throw new FreecodeException("dataSource type not support");
            }

            List<TableColumnInfo> tableColumnInfos = jdbcTemplate.queryForList(sqlStr, TableColumnInfo.class);

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
            }
            return tempTableColumnInfos;
        }

        throw new FreecodeException("dataSource is not exist");
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
        return jdbcTemplate.queryForObject(sqlStr, TableInfo.class);
    }
}
