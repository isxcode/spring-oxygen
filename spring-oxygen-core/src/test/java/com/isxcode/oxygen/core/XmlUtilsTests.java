package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.pojo.Dog;
import com.isxcode.oxygen.core.xml.XmlUtils;
import org.junit.jupiter.api.Test;

public class XmlUtilsTests {

    @Test
    public void testXml() {

        String xmlStr = "<xml><name>wang</name><age>12</age></xml>";
        Dog dog = XmlUtils.parseXmlString(xmlStr, Dog.class);
        System.out.println("dog:" + dog.toString());
    }

}

