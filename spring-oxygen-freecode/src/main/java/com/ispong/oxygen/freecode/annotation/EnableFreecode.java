package com.ispong.oxygen.freecode.annotation;


import com.ispong.oxygen.freecode.FreecodeAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * freecode 启动器
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FreecodeAutoConfiguration.class)
@Documented
public @interface EnableFreecode {

}
