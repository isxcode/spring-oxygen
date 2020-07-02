package com.ispong.oxygen.common.reflect;

import com.ispong.oxygen.common.exception.CoreException;

import java.lang.reflect.InvocationTargetException;

public class ReflectUtils {


    public static <T> T newInstance(Class<T> targetClass) {

        try {

            return targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CoreException("reflect instance fail");
        }
    }

}
