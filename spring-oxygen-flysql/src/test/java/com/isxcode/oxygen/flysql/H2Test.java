package com.isxcode.oxygen.flysql;//package com.isxcode.oxygen.flysql;
//
//import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
//import com.isxcode.oxygen.flysql.core.Flysql;
//import com.isxcode.oxygen.flysql.Dog;
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
//public class FlysqlDeleteTests {
//
//    @Test
//    public void testDelete() {
//
//        Flysql.delete(Dog.class).eq("name", "tom").doDelete();
//        List<Dog> dogs = Flysql.select(Dog.class).query();
//        System.out.println(dogs);
//    }
//}
