// package com.isxcode.oxygen.freecode;
//
// import com.isxcode.oxygen.core.config.OxygenCoreAutoConfiguration;
// import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
// import com.isxcode.oxygen.freecode.config.FreecodeAutoConfiguration;
// import com.isxcode.oxygen.freecode.service.FreecodeService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.ContextConfiguration;
//
// @AutoConfigureJdbc
// @SpringBootTest
// @ContextConfiguration(classes = {
//    FreecodeAutoConfiguration.class,
//    FlysqlAutoConfiguration.class,
//    OxygenCoreAutoConfiguration.class,
//    FreeMarkerAutoConfiguration.class,
//    })
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @ActiveProfiles
// public class OxygenFreecodeTests {
//
//    @Autowired
//    private FreecodeService freecodeService;
//
//    @Test
//    public void testGenerateCode() {
//
//        freecodeService.startFreecode("leo_dogs");
//    }
// }
//
