package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.config.OxygenCoreAutoConfiguration;
import com.isxcode.oxygen.core.freemarker.FreemarkerUtils;
import com.isxcode.oxygen.core.pojo.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles
@ContextConfiguration(classes = OxygenCoreAutoConfiguration.class)
public class CoreFreemarkerTests {

    private final Dog dog = new Dog("wang", 15);

    @Test
    public void testFreemarkerByString() {

        String templateContent = "dog name is ${name},age is ${age}";
        String data = FreemarkerUtils.generateToString(templateContent, dog);
        System.out.println("data-->" + data);
    }
}

