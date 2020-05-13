package com.ispong.oxygen.module.log;

import com.ispong.oxygen.flysql.Flysql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogRepository {

    public void saveLog(LogEntity logEntity) {

        Flysql.insert(LogEntity.class).save(logEntity);
    }

    public List<LogEntity> queryLog() {

        return Flysql.insert(LogEntity.class).query();
    }
}
