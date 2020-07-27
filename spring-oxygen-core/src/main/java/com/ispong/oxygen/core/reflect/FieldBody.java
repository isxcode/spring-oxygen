package com.ispong.oxygen.core.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Data
public class FieldBody {

    private Method readMethod;

    private Method writeMethod;

    private Field field;

    private String className;
}
