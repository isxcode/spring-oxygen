package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.excel.ExcelUtils;
import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.pojo.Dog;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilsTests {

//    @Test
//    public void testExcelParseFile() {
//
//        try {
//            List<Dog> dogs = ExcelUtils.parseFile(new FileInputStream("F://test.xlsx"), Dog.class);
//            dogs.forEach(e -> System.out.println(e.toString()));
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    @Test
//    public void testGenerateExcelFile() {
//
//        List<Dog> dogs = new ArrayList<>();
//        dogs.add(new Dog("alen", 12));
//        dogs.add(new Dog("jack", 14));
//        dogs.add(new Dog("johy", 18));
//
//        try {
//            ExcelUtils.generateFile(dogs, new FileOutputStream("F://test.xlsx"));
//        } catch (OxygenException | IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
