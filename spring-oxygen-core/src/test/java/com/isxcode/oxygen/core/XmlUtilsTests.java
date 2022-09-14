package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.pojo.Dog;
import com.isxcode.oxygen.core.xml.XmlUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlUtilsTests {

    @Test
    public void testXml() {

        String xmlStr = "<xml><name>wang</name><age>12</age></xml>";
        Path path = Paths.get("Dogs.xml");
        try {
            Files.write(path, xmlStr.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Dog dog = XmlUtils.parseXmlString(xmlStr, Dog.class);
        System.out.println("dog:" + dog);
    }

    @Test
    public void testXmlFile() {

        Dog dog;
        Path path = Paths.get("Dogs.xml");
        try {
            dog = XmlUtils.parseXmlInputStream(Files.newInputStream(path), Dog.class);
            System.out.println("dog:" + dog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

