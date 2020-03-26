package com.ispong.oxygen.module.log;

import java.lang.annotation.*;

/**
 * 记录所有接口日志
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Logs {

    /**
     * 以下接口不调用日志
     */
    String[] excludes() default {};
}
