package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.excel.ExcelUtils;
import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.pojo.Dog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilsTests {

    @Test
    public void testGenerateExcelFile() {

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("alen", 12));
        dogs.add(new Dog("jack", 14));
        dogs.add(new Dog("john", 18));

        try {
            ExcelUtils.generateFile(dogs, Files.newOutputStream(Paths.get("Dogs.xlsx")));
        } catch (OxygenException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testExcelParseFile() {

        try {
            Path path = Paths.get("Dogs.xlsx");
            List<Dog> dogs = ExcelUtils.parseFile(Files.newInputStream(path), Dog.class);
            dogs.forEach(e -> System.out.println(e.toString()));
            Files.delete(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
