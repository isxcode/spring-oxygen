package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
import com.isxcode.oxygen.flysql.entity.FlysqlPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JdbcTest
@ContextConfiguration(classes = FlysqlAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles
public class TestJdbc {

    private final Flysql flysql;

    public TestJdbc(@Qualifier("flysql") Flysql flysql) {

        this.flysql = flysql;
    }

    @Test
    public void testJdbc() {

        MetaData metaData1 = null;
        MetaData metaData2 = null;
        MetaData metaData3 = null;

        ArrayList<MetaData> metaList = new ArrayList<>();

        try {
            metaData1 = new MetaData("data1", new Date(), LocalDate.now(), LocalDateTime.now(), new BigDecimal("1"), '1', 1, 1.1, 1L, true, Short.parseShort("1"), 1f, Byte.parseByte("1"));
            metaData2 = new MetaData("data2", new Date(), LocalDate.now(), LocalDateTime.now(), new BigDecimal("2"), '2', 2, 2.2, 2L, true, Short.parseShort("2"), 2f, Byte.parseByte("2"));
            metaData3 = new MetaData("data3", new Date(), LocalDate.now(), LocalDateTime.now(), new BigDecimal("3"), '3', 3, 3.3, 3L, true, Short.parseShort("3"), 3f, Byte.parseByte("3"));
        } catch (NumberFormatException ignored) {

        }

        metaList.add(metaData2);
        metaList.add(metaData3);

        flysql.build().insert(MetaData.class).save(metaData1);
        flysql.build().insert(MetaData.class).batchSave(metaList);

        System.out.println("=========================== show all data   ====================================");
        List<MetaData> metaDataList = flysql.build().select(MetaData.class).query();
        metaDataList.forEach(System.out::println);

        System.out.println("=========================== show page data   ====================================");
        FlysqlPage<MetaData> flysqlPage = flysql.build().select(MetaData.class).isNotNull("anDate").queryPage(1, 2);
        System.out.println(flysqlPage);

        System.out.println("============================ show single data ===================================");
        MetaData metaData = flysql.build().select(MetaData.class).eq("anString", "data1").getOne();
        System.out.println(metaData);

        System.out.println("============================= show after delete data =================================");
        flysql.build().delete(MetaData.class).eq("anString", "data1").doDelete();
        metaDataList = flysql.build().select(MetaData.class).query();
        metaDataList.forEach(System.out::println);

        System.out.println("============================== show after update data  =================================");
        flysql.build().update(MetaData.class).eq("anString", "data3")
            .update("anInt", "4")
            .update("anDouble", "4.4")
            .update("anDate", new Date())
            .doUpdate();
        metaDataList = flysql.build().select(MetaData.class).query();
        metaDataList.forEach(System.out::println);
    }
}
