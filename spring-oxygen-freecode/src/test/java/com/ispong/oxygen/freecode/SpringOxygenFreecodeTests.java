package com.ispong.oxygen.freecode;

import com.ispong.oxygen.core.config.OxygenCoreAutoConfiguration;
import com.ispong.oxygen.flysql.config.FlysqlAutoConfiguration;
import com.ispong.oxygen.freecode.config.FreecodeAutoConfiguration;
import com.ispong.oxygen.freecode.service.FreecodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@AutoConfigureJdbc
@SpringBootTest
@ContextConfiguration(classes = {
    FreecodeAutoConfiguration.class,
    FlysqlAutoConfiguration.class,
    OxygenCoreAutoConfiguration.class,
    FreeMarkerAutoConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles
public class SpringOxygenFreecodeTests {

    @Autowired
    private FreecodeService freecodeService;

    @Test
    public void testGenerateCode() {

        freecodeService.startFreecode("leo_files");
    }
}

