package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
import com.isxcode.oxygen.flysql.entity.FlysqlPage;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;
import org.junit.jupiter.api.Disabled;
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

@Disabled
@JdbcTest
@ContextConfiguration(classes = {EnableEncryptablePropertiesConfiguration.class, FlysqlAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("sqlserver")
public class TestSqlServer {

    private final Flysql flysql;

    public TestSqlServer(@Qualifier("flysql") Flysql flysql) {

        this.flysql = flysql;
    }

    @Test
    public void testFlysql() {

        Dog dog1 = null;
        Dog dog2 = null;
        Dog dog3 = null;

        ArrayList<Dog> dogList = new ArrayList<>();
        try {
            dog1 = new Dog(1, "jack", 1.1, new BigDecimal("1.1"), new Date(), LocalDate.now(), LocalDateTime.now(), true,0);
            dog2 = new Dog(2, "john", 1.2, new BigDecimal("1.2"), new Date(), LocalDate.now(), LocalDateTime.now(), true,0);
            dog3 = new Dog(3, "rose", 1.3, new BigDecimal("1.3"), new Date(), LocalDate.now(), LocalDateTime.now(), true,0);
        } catch (NumberFormatException ignored) {
        }

        dogList.add(dog2);
        dogList.add(dog3);

        flysql.build().insert(Dog.class).save(dog1);
        flysql.build().insert(Dog.class).batchSave(dogList);

        System.out.println("=========================== show all data   ====================================");
        List<Dog> dogQuery = flysql.build().select(Dog.class).query();
        dogQuery.forEach(System.out::println);

//        System.out.println("=========================== show page data   ====================================");
//        FlysqlPage<Dog> dogQueryPage = flysql.build().select(Dog.class).queryPage(1, 2);
//        System.out.println(dogQueryPage);

        System.out.println("============================ show single data ===================================");
        Dog dogGetOne = flysql.build().select(Dog.class).eq("name", "rose").getOne();
        System.out.println(dogGetOne);

        System.out.println("============================= show after delete data =================================");
        flysql.build().delete(Dog.class).eq("name", "john").doDelete();
        dogQuery = flysql.build().select(Dog.class).query();
        dogQuery.forEach(System.out::println);

        System.out.println("============================== show after update data  =================================");
        flysql.build().update(Dog.class)
            .eq("name", "jack")
            .update("amountDouble", 9.9)
            .doUpdate();
        dogQuery = flysql.build().select(Dog.class).query();
        dogQuery.forEach(System.out::println);
    }
}
