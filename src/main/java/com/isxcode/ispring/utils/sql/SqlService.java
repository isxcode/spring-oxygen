package com.isxcode.ispring.utils.sql;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlService {

    private final SqlDao sqlDao;

    public SqlService(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }

    public List<SqlUser> getOne() {

        return sqlDao.queryEntity();
    }

}
