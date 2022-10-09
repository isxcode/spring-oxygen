package com.isxcode.oxygen.freecode.annotation;

import com.isxcode.oxygen.freecode.config.FreecodeAutoConfiguration;
import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

/**
 * freecode starter
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FreecodeAutoConfiguration.class)
@Documented
public @interface EnableFreecode {}
