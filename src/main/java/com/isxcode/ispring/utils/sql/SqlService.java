package com.isxcode.ispring.utils.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


// 主动提供一些方法给用户去使用
@Service
public class SqlService{

    private final SqlDao sqlDao;

    public SqlService(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }

    public void getOne() {
        sqlDao.getOne();
    }

    // 分页查询


}
