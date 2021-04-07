package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@DataMongoTest
@ContextConfiguration(classes = {FlysqlAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles
public class FlysqlMongdbTests {

    private final Flysql flysql;

    public FlysqlMongdbTests(@Qualifier("flysqlFactory") Flysql flysql) {

        this.flysql = flysql;
    }

    @Test
    public MetaData getOne(String name) {

        return flysql.build().select(MetaData.class)
            .isNotNull("name")
            .getOne();
    }
}
