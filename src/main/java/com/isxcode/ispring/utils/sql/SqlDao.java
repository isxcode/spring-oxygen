package com.isxcode.ispring.utils.sql;

import com.isxcode.ispring.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class SqlDao {

    List<SqlUser> queryEntity() {

        return SqlFactory.selectSql(SqlUser.class)
                .eq("createdBy", "ispong")
                .orderBy("uuid", "desc")
                .query();
    }

    List<UserEntity> query() {

        return SqlFactory.selectSql(UserEntity.class)
                .eq("createdBy", "ispong")
                .orderBy("uuid", "desc")
                .query();
    }

}
