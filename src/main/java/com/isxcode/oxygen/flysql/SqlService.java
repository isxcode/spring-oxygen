package com.isxcode.oxygen.flysql;

import org.springframework.stereotype.Service;


// 主动提供一些方法给用户去使用
@Service
public class SqlService {

    private final SqlDao sqlDao;

    public SqlService(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }

    public void getOne() {
        sqlDao.getOne();
    }

    // 分页查询


}
