package com.isxcode.oxygen.flysql.annotation;

import com.isxcode.oxygen.flysql.enums.IdGenerateType;
import java.lang.annotation.*;

/**
 * sys column id
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RowId {

	IdGenerateType generateType() default IdGenerateType.SNOW_FLACK;
}
