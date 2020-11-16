package com.ispong.oxygen.core;

import com.ispong.oxygen.core.xml.XmlMarker;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class CoreXmlTests {

    @Test
    public void testXml() {
        String xmlStr = "<xml><name>wang</name><age>12</age></xml>";
        Dog dog = XmlMarker.parseInputStreamXml(new ByteArrayInputStream(xmlStr.getBytes()), Dog.class);
        System.out.println("dog==>" + dog.getName() + ":" + dog.getAge());
    }
}

