package com.isxcode.ispring.sql;

import com.isxcode.ispring.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class SqlDao {

    @Autowired
    private DataSource dataSource;

    /**
     * 不能使用数据里面的字段 直接剥离数据库
     *
     * @since 2019-12-30
     */
    List<SqlUser> queryEntity() {

        return SqlFactory.selectSql(SqlUser.class)
                .query();

    }


    /**
     * 不使用数据里面的字段 直接剥离数据库
     *
     * @since 2019-12-30
     */
    SqlUser getOne() {

        return SqlFactory.selectSql(SqlUser.class)
                .select("userName", "password", "age")
                .eq("uuid", "ispong")
                .ltEq("createDate",new Date())
                .getOne();

    }


    // 使用注解 实现  只需要关注dao层 即可关注所有的数据库操作
    // 自己封装一下jdbc


    List<UserEntity> query() {

        // 怎么样提高sql查询  缓存时毋庸置疑的
        // 分析myBatis 当生成sql语句之后 做了哪些优化操作
        // Create a new application context. this processes the Spring config
//
        return null;

    }

}
