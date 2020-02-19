package com.ispong.oxygen.flysql;

import com.ispong.oxygen.flysql.annotation.DateBaseType;
import com.ispong.oxygen.flysql.annotation.FlysqlView;
import com.ispong.oxygen.flysql.annotation.FlysqlViews;
import com.ispong.oxygen.flysql.model.SqlCondition;
import com.ispong.oxygen.flysql.model.SqlStatement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * sql builder
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class SqlExecutor<A> extends AbstractSqlBuilder<SqlExecutor<A>> implements SqlOperations {

    @Getter
    private Class<A> genericType;

    private static JdbcTemplate jdbcTemplate;

    private String sqlType;

    private DateBaseType dateBaseType;

    public SqlExecutor(Class<A> genericType, String sqlType, JdbcTemplate jdbcTemplate, DateBaseType dateBaseType) {

        this.dateBaseType = dateBaseType;
        this.sqlType = sqlType;
        this.genericType = genericType;
        SqlExecutor.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SqlExecutor<A> getSelf() {

        return this;
    }

    private String parseSql(SqlStatement sqlStatement) {

        String tableName = sqlStatement.getTableName();
        List<SqlCondition> conditionValues = sqlStatement.getConditionValues();

        return "select" + String.join(",", sqlStatement.getColumnNames()) + "from" + tableName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public A getOne() {

        if (sqlType.equals(FlysqlConstants.SQL_VIEW)) {

            FlysqlView[] flysqlViews = getGenericType().getAnnotation(FlysqlViews.class).value();
            for (FlysqlView flysqlViewMeta : flysqlViews) {
                if (flysqlViewMeta.type().equals(DateBaseType.MYSQL)) {

                    String sqlString = flysqlViewMeta.value();
                    var ref = new Object() {
                        String sql = "";
                    };
                    sqlStatement.setValues.forEach((k, v) -> {
                        ref.sql = sqlString.replaceAll(k, v);
                    });
                    log.debug("sql:" + ref.sql);
                    return jdbcTemplate.queryForObject(ref.sql, new BeanPropertyRowMapper<>(getGenericType()));
                }
            }
        }

        return jdbcTemplate.queryForObject(parseSql(sqlStatement), new BeanPropertyRowMapper<>(getGenericType()));
    }


//    @Override
//    @SuppressWarnings("unchecked")
//    public List<A> query() {
//
//        return jdbcTemplate.query(parseSql(), new BeanPropertyRowMapper<>(getGenericType()));
//    }


//    @Override
//    @SuppressWarnings("unchecked")
//    public List<A> query(Integer page, Integer size) {
//
//        return jdbcTemplate.query(getSqlStr().toString(), new BeanPropertyRowMapper<>(getGenericType()));
//    }
//
//    @Override
//    public void doUpdate() {
//
//        jdbcTemplate.update(getSqlStr().toString());
//    }
//
//    @Override
//    public void batchUpdate() {
//
//        jdbcTemplate.batchUpdate(getSqlStr().toString());
//    }
//
//    @Override
//    public void save() {
//
//        jdbcTemplate.update(getSqlStr().toString());
//    }
//
//    @Override
//    public void doDelete() {
//
//        jdbcTemplate.update(getSqlStr().toString());
//    }
}
