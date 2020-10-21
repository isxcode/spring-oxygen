package com.ispong.oxygen.flysql.success;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SuccessResponse {

    String msg();

}
