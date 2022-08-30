package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.isxcode.oxygen.flysql.core.Flysql;
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
import java.util.Date;
import java.util.List;

@Disabled
@JdbcTest
@ContextConfiguration(classes = {EnableEncryptablePropertiesConfiguration.class, FlysqlAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("oracle")
public class TestOracle {

    private final Flysql flysql;

    public TestOracle(@Qualifier("flysql") Flysql flysql) {

        this.flysql = flysql;
    }

    @Test
    public void testFlysql() {

        Dog dog1 = null;
        Dog dog2 = null;
        Dog dog3 = null;

        try {
            dog1 = new Dog(1, "jack", 1.1, new BigDecimal("1.1"), new Date(), LocalDate.now(), LocalDateTime.now(), true);
            dog2 = new Dog(2, "john", 1.2, new BigDecimal("1.2"), new Date(), LocalDate.now(), LocalDateTime.now(), true);
            dog3 = new Dog(3, "rose", 1.3, new BigDecimal("1.3"), new Date(), LocalDate.now(), LocalDateTime.now(), true);
        } catch (NumberFormatException ignored) {
        }

        flysql.build().insert(Dog.class).save(dog1);
        flysql.build().insert(Dog.class).save(dog2);
        flysql.build().insert(Dog.class).save(dog3);

        System.out.println("=========================== show all data   ====================================");
        List<Dog> dogQuery = flysql.build().select(Dog.class).query();
        dogQuery.forEach(System.out::println);

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
