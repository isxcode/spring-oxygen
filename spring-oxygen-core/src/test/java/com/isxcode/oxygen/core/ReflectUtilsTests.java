package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.pojo.Dog;
import com.isxcode.oxygen.core.reflect.FieldBody;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ReflectUtilsTests {

	@Test
	public void testNewInstance() {

		Dog dog = ReflectUtils.newInstance(Dog.class);
		dog.setAge(12);
		dog.setName("alen");
		System.out.println("dog:" + dog);
	}

	@Test
	public void testQueryFieldsBody() {

		List<FieldBody> fieldBodies = ReflectUtils.queryFields(Dog.class);
		Dog dog = new Dog();
		for (FieldBody metaFieldBody : fieldBodies) {
			Method writeMethod = metaFieldBody.getWriteMethod();
			Method readMethod = metaFieldBody.getReadMethod();
			try {
				if ("name".equals(metaFieldBody.getField().getName())) {
					writeMethod.invoke(dog, "alen");
					Object dogName = readMethod.invoke(dog);
					System.out.println("dogName" + dogName);
					System.out.println(dog);
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testName() {

		System.out.println(ReflectUtils.upperFirstCase("userName"));

		System.out.println(ReflectUtils.humpToLine("userName"));

		System.out.println(ReflectUtils.lineToHump("user_name"));
	}
}
