package com.ispong.oxygen.core;

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
}

