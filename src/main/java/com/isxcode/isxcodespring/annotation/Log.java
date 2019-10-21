package com.isxcode.isxcodespring.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解- Log
 *
 * @author ispong
 * @date 2019/10/21
 * @version v0.1.0
 */
@Target(value={ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Log {

}
