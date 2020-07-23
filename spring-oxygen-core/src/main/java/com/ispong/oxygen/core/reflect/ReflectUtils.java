package com.ispong.oxygen.core.reflect;

import com.ispong.oxygen.core.exception.OxygenException;

import java.lang.reflect.InvocationTargetException;

public class ReflectUtils {


    public static <T> T newInstance(Class<T> targetClass) {

        try {

            return targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new OxygenException("reflect instance fail");
        }
    }

}
