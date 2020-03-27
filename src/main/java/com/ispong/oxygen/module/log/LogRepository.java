package com.ispong.oxygen.module.log;

import com.ispong.oxygen.flysql.Flysql;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepository {

    public void saveLog(LogEntity logEntity) {

        Flysql.insert(LogEntity.class).save(logEntity);
    }
}
