package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.config.OxygenCoreAutoConfiguration;
import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.freemarker.FreemarkerUtils;
import com.isxcode.oxygen.core.pojo.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootTest
@ActiveProfiles
@ContextConfiguration(classes = {OxygenCoreAutoConfiguration.class, FreeMarkerConfigurer.class})
public class FreemarkerUtilsTests {

    private final Dog dog = new Dog("wang", 15);

    private final String templateContent = "this dog name is ${name} and age is ${age}";

    @Test
    public void testContentToFile() {
        try {
            FreemarkerUtils.contentToFile(templateContent, dog, "D://test");
        } catch (OxygenException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testContentToString() {

        try {
            System.out.println(FreemarkerUtils.contentToString(templateContent, dog));
        } catch (OxygenException e) {
            System.out.println(e.getMessage());
        }
    }
}

