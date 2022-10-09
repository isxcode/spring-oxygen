package com.isxcode.oxygen.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class properties
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldBody {

	/** read method */
	private Method readMethod;

	/** write method */
	private Method writeMethod;

	/** field */
	private Field field;

	/** className */
	private String className;
}
