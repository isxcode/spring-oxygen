package com.isxcode.ispring.utils.sql;

import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.utils.AnnotationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sql 执行器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-23
 */
@Slf4j
@Component
public class SqlBuilder<A> extends AbstractSqlBuilder<SqlBuilder<A>> implements SqlOperations {

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

        try {
            log.info("sqlStr:" + getSqlStr().toString());
            // 初始化返回对象
            List<A> result = new ArrayList<>();
            // 遍历解析数据库返回对象
            for (Map<String, Object> metaBean : jdbcTemplate.queryForList(getSqlStr().toString())) {
                result.add((A) AnnotationUtils.mapToBean(metaBean, getBeanClazz()));
            }
            return result;
        } catch (Exception e) {
            throw new IsxcodeException("数据库操作异常");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public A getOne() {

        try {
            log.info("sqlStr:" + getSqlStr().toString());
            return (A) AnnotationUtils.mapToBean(jdbcTemplate.queryForMap(getSqlStr().toString()), getBeanClazz());
        } catch (Exception e) {
            throw new IsxcodeException("数据库操作异常");
        }
    }

    @Override
    public void doUpdate() {

    }

    @Override
    public void save() {

    }
}
