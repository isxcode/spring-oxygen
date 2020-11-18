package com.ispong.oxygen.flysql;

import com.ispong.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.ispong.oxygen.flysql.core.Flysql;
import com.ispong.oxygen.flysql.pojo.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@JdbcTest
@ContextConfiguration(classes = FlysqlAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles
public class SpringOxygenFlysqlTests {

    @Test
    public void testSelect() {

        List<Dog> dogs = Flysql.select(Dog.class).query();
        System.out.println(dogs);
    }

    @Test
    public void testUpdate() {

        Flysql.update(Dog.class)
            .update("age", 14)
            .eq("name", "john")
            .doUpdate();

        List<Dog> dogs = Flysql.select(Dog.class).query();
        System.out.println(dogs);
    }

    @Test
    public void testInsert() {

        Dog jack = new Dog("jack", 12);
        Flysql.insert(Dog.class).save(jack);

        List<Dog> dogs = Flysql.select(Dog.class).query();
        System.out.println(dogs);
    }

    @Test
    public void testDelete() {

        Flysql.delete(Dog.class)
            .eq("name", "john")
            .doDelete();

        List<Dog> dogs = Flysql.select(Dog.class).query();
        System.out.println(dogs);
    }
}