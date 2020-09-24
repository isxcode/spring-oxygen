package com.ispong.oxygen.flysql;

import com.ispong.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.ispong.oxygen.flysql.core.Flysql;
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
public class FlysqlTest {

    @Test
    public void testDog() {

        List<Dog> dogs = Flysql.select(Dog.class).query();
        System.out.println(dogs);
    }
}