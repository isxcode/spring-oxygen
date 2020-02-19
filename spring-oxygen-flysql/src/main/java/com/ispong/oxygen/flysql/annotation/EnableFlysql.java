package com.ispong.oxygen.flysql.annotation;

import com.ispong.oxygen.flysql.FlysqlAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 *
 *
 * @author ispong
 * @since  0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FlysqlAutoConfiguration.class)
@Documented
public @interface EnableFlysql {

}
