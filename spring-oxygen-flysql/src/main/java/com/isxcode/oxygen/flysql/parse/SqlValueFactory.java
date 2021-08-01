package com.isxcode.oxygen.flysql.parse;

import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import org.springframework.lang.NonNull;

public class SqlValueFactory {

    public static SqlValue getSqlValue(@NonNull DataBaseType dataBaseType) {

        switch (dataBaseType) {
            case ORACLE:
                return new OracleSqlValue();
            case H2:
                return new H2SqlValue();
            case MYSQL:
                return new MysqlSqlValue();
            case MONGO:
                return new MongoSqlValue();
            case SQL_SERVER:
                return new SqlServerSqlValue();
            default:
                throw new OxygenException("not support db");
        }
    }
}
