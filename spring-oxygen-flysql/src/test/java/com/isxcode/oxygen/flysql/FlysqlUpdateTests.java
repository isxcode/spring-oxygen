//package com.isxcode.oxygen.flysql;
//
//
//import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
//import com.isxcode.oxygen.flysql.core.Flysql;
//import com.isxcode.oxygen.flysql.pojo.Dog;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.List;
//
//@JdbcTest
//@ContextConfiguration(classes = FlysqlAutoConfiguration.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles
//public class FlysqlUpdateTests {
//
//    @Test
//    public void testSelect() {
//
//        Flysql.update(Dog.class)
//            .update("age", 15)
//            .eq("name", "john")
//            .doUpdate();
//
//        List<Dog> dogs = Flysql.select(Dog.class).query();
//        System.out.println(dogs);
//    }
//}
