package com.isxcode.ispring.flysql;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * sql jdbcTemplate最终执行器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-23
 */
@Slf4j
@Component
public class SqlBuilder<A> extends AbstractSqlBuilder<SqlBuilder<A>> implements SqlOperations {

    /**
     * 暂存泛型类型
     */
    @Getter
    @Setter
    private Class<A> genericType;

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        SqlBuilder.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SqlBuilder<A> getSelf() {

        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<A> query() {

        return jdbcTemplate.query(getSqlStr().toString(), new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public A getOne() {

        return jdbcTemplate.queryForObject(getSqlStr().toString(), new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<A> query(Integer page, Integer size) {

        return jdbcTemplate.query(getSqlStr().toString(), new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    public void doUpdate() {

        jdbcTemplate.update(getSqlStr().toString());
    }

    @Override
    public void batchUpdate() {

        jdbcTemplate.batchUpdate(getSqlStr().toString());
    }

    @Override
    public void save() {

        jdbcTemplate.update(getSqlStr().toString());
    }

    @Override
    public void doDelete() {

        jdbcTemplate.update(getSqlStr().toString());
    }
}
