package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@JdbcTest
@DataMongoTest
@ContextConfiguration(classes = FlysqlAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles
public class FlysqlSelectTests {

    private final Flysql flysql;

    public FlysqlSelectTests(Flysql flysql) {
        this.flysql = flysql;
    }

    @Test
    public void testSelect() {

        List<MetaData> metaDataList = flysql.build().select(MetaData.class).query();

        System.out.println("================= Result:");
        metaDataList.forEach(System.out::println);
        System.out.println("=========================");
    }
}
