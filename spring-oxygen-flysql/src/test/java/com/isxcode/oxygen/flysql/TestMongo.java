package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
import com.isxcode.oxygen.flysql.entity.FlysqlPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Disabled
@DataMongoTest
@ContextConfiguration(classes = {FlysqlAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mongo")
public class TestMongo {

    private final Flysql flysql;

    public TestMongo(@Qualifier("flysqlFactory") Flysql flysql) {

        this.flysql = flysql;
    }

    @Test
    public void testMongo() {

        Dog dog1 = null;
        Dog dog2 = null;
        Dog dog3 = null;

        ArrayList<Dog> dogList = new ArrayList<>();
        try {
            dog1 = new Dog(1, "jack", 1.1, new BigDecimal("1.1"), new Date(), LocalDate.now(), LocalDateTime.now(), true,1);
            dog2 = new Dog(2, "john", 1.2, new BigDecimal("1.2"), new Date(), LocalDate.now(), LocalDateTime.now(), true,1);
            dog3 = new Dog(3, "rose", 1.3, new BigDecimal("1.3"), new Date(), LocalDate.now(), LocalDateTime.now(), true,1);
        } catch (NumberFormatException ignored) {
        }

        dogList.add(dog2);
        dogList.add(dog3);

        flysql.buildMongo().insert(Dog.class).save(dog1);
        flysql.buildMongo().insert(Dog.class).batchSave(dogList);

        System.out.println("=========================== show all data   ====================================");
        List<Dog> dogQuery = flysql.buildMongo().select(Dog.class).query();
        dogQuery.forEach(System.out::println);

        System.out.println("=========================== show page data   ====================================");
        FlysqlPage<Dog> dogQueryPage = flysql.buildMongo().select(Dog.class).queryPage(1, 2);
        System.out.println(dogQueryPage);

        System.out.println("============================ show single data ===================================");
        Dog dogGetOne = flysql.buildMongo().select(Dog.class).eq("name", "rose").getOne();
        System.out.println(dogGetOne);

        System.out.println("============================= show after delete data =================================");
        flysql.buildMongo().delete(Dog.class).eq("name", "john").doDelete();
        dogQuery = flysql.buildMongo().select(Dog.class).query();
        dogQuery.forEach(System.out::println);

        System.out.println("============================== show after update data  =================================");
        flysql.buildMongo().update(Dog.class)
            .eq("name", "jack")
            .update("amountDouble", 9.9)
            .doUpdate();
        dogQuery = flysql.buildMongo().select(Dog.class).query();
        dogQuery.forEach(System.out::println);
    }
}
