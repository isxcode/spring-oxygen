package com.ispong.oxygen.core;

import com.ispong.oxygen.core.exception.OxygenException;
import com.ispong.oxygen.core.pojo.Dog;
import com.ispong.oxygen.core.xml.XmlUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class CoreXmlTests {

    @Test
    public void testXml() {
        String xmlStr = "<xml><name>wang</name><age>12</age></xml>";
        Dog dog = XmlUtils.parseInputStreamXml(new ByteArrayInputStream(xmlStr.getBytes()), Dog.class);
        System.out.println("dog==>" + dog.getName() + ":" + dog.getAge());
    }

    @Test
    public void testXmlForException() {
        String xmlStr = "<xml><namewang</name><age>12</age></xml>";
        try {
            XmlUtils.parseInputStreamXml(new ByteArrayInputStream(xmlStr.getBytes()), Dog.class);
        }catch (OxygenException e){
            System.out.println("xml parse  exception");
        }
    }
}

