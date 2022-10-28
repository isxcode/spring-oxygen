package com.isxcode.oxygen.cli.annotation;

import com.isxcode.oxygen.cli.config.FreecodeAutoConfiguration;
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
