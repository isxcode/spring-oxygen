package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.pojo.Dog;
import com.isxcode.oxygen.core.reflect.FieldBody;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CoreReflectTests {

    @Test
    public void testNewInstance() {

        Dog dog = ReflectUtils.newInstance(Dog.class);
        System.out.println("data-->" + dog.toString());
    }

    @Test
    public void testQueryFields() {

        List<FieldBody> fieldBodies = ReflectUtils.queryFields(Dog.class);
        for (FieldBody metaBody : fieldBodies) {
            System.out.println(metaBody.getClassName());
            metaBody.getField();
            metaBody.getReadMethod();
            metaBody.getWriteMethod();
        }
    }
}
