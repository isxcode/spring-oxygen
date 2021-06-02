package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@JdbcTest
@ContextConfiguration(classes = FlysqlAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles
public class TestJdbc {

    private final Flysql flysql;

    public TestJdbc(Flysql flysql) {

        this.flysql = flysql;
    }

    @Test
    public void testDebug(){

        System.out.println("测试vscode debug");
    }

    @Test
    public void testJdbc() {

        // 插入数据
        MetaData metaData1 = new MetaData("data1", new Date(), LocalDate.now(), LocalDateTime.now(), new BigDecimal("1"), '1', 1, 1.1, 1L, true, Short.parseShort("1"), 1f, Byte.parseByte("1"));
        MetaData metaData2 = new MetaData("data2", new Date(), LocalDate.now(), LocalDateTime.now(), new BigDecimal("2"), '2', 2, 2.2, 2L, true, Short.parseShort("2"), 2f, Byte.parseByte("2"));
        MetaData metaData3 = new MetaData("data3", new Date(), LocalDate.now(), LocalDateTime.now(), new BigDecimal("3"), '3', 3, 3.3, 3L, true, Short.parseShort("3"), 3f, Byte.parseByte("3"));

        flysql.build().insert(MetaData.class).save(metaData1);
        flysql.build().insert(MetaData.class).save(metaData2);
        flysql.build().insert(MetaData.class).save(metaData3);

        // 查询所有数据
        List<MetaData> metaDataList = flysql.build().select(MetaData.class).query();
        metaDataList.forEach(System.out::println);
        System.out.println("===============================================================");

        // 查询单条数据
        MetaData metaData = flysql.build().select(MetaData.class).eq("anString", "data1").getOne();
        System.out.println(metaData);
        System.out.println("===============================================================");

        // 条件删除数据
        flysql.build().delete(MetaData.class).eq("anString", "data1").doDelete();
        metaDataList = flysql.build().select(MetaData.class).query();
        metaDataList.forEach(System.out::println);
        System.out.println("===============================================================");

        // 单条更新
        flysql.build().update(MetaData.class).eq("anString", "data3")
            .update("anInt", "4")
            .update("anDouble", "4.4")
            .update("anDate", new Date())
            .doUpdate();
        metaDataList = flysql.build().select(MetaData.class).query();
        metaDataList.forEach(System.out::println);
        System.out.println("===============================================================");
    }
}
