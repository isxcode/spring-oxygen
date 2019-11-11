package com.isxcode.isxcodespring.annotation;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class TestAspect extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {

        return false;
    }

    public void test(){
        System.out.println("测试test注解");
    }
}
