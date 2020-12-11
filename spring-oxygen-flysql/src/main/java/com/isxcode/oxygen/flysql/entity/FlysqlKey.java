package com.isxcode.oxygen.flysql.entity;

import com.isxcode.oxygen.flysql.enums.SqlType;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * flysql的构造器的核心要素
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class FlysqlKey<A> {

    /**
     * 临时返回对象
     */
    private Class<A> targetClass;

    /**
     * 数据源对应的jdbcTemplate
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * 视图名称
     */
    private String viewSqlName;

    /**
     * 执行器类型
     */
    private SqlType sqlType;

    public FlysqlKey(SqlType sqlType, JdbcTemplate jdbcTemplate, Class<A> targetClass, String viewSqlName) {
        this.targetClass = targetClass;
        this.jdbcTemplate = jdbcTemplate;
        this.viewSqlName = viewSqlName;
        this.sqlType = sqlType;
    }

    public FlysqlKey(SqlType sqlType, JdbcTemplate jdbcTemplate, Class<A> targetClass) {
        this.targetClass = targetClass;
        this.jdbcTemplate = jdbcTemplate;
        this.sqlType = sqlType;
    }
}
