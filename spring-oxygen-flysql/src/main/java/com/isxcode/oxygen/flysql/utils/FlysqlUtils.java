package com.isxcode.oxygen.flysql.utils;

import com.isxcode.oxygen.core.reflect.ReflectConstants;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.flysql.annotation.ColumnName;
import com.isxcode.oxygen.flysql.annotation.TableName;
import com.isxcode.oxygen.flysql.entity.ColumnProperties;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeanUtils;

/**
 * flysql utils
 *
 * @author ispong
 * @since 0.0.2
 */
public class FlysqlUtils {

	/**
	 * parse class to map 记录对象，各个字段的映射关系
	 *
	 * @param genericType targetClass
	 * @return Map[propertiesName, type]
	 * @since 0.0.1
	 */
	public static Map<String, ColumnProperties> parseBeanProperties(Class<?> genericType) {

		// 使用spring自带工具进行解析
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(genericType);
		// 返回结果
		Map<String, ColumnProperties> columnNameMap = new HashMap<>(propertyDescriptors.length - 1);
		for (PropertyDescriptor propertyMeta : propertyDescriptors) {
			// 排除class类型
			if (!ReflectConstants.CLASS.equals(propertyMeta.getName())) {
				ColumnProperties metaColumnProperty = new ColumnProperties();
				metaColumnProperty.setType(propertyMeta.getPropertyType().getName());
				try {
					Field metaField =
							propertyMeta
									.getReadMethod()
									.getDeclaringClass()
									.getDeclaredField(propertyMeta.getName());
					// 如果用户自定义注解，则使用用户自己定义的字段
					if (metaField.isAnnotationPresent(ColumnName.class)) {
						metaColumnProperty.setName(metaField.getAnnotation(ColumnName.class).value());
					} else {
						metaColumnProperty.setName(ReflectUtils.humpToLine(propertyMeta.getName()));
					}
					columnNameMap.put(propertyMeta.getName(), metaColumnProperty);
				} catch (NoSuchFieldException e) {
					// 如果映射失败，则默认使用下划线分割字段名
					metaColumnProperty.setName(ReflectUtils.humpToLine(propertyMeta.getName()));
					columnNameMap.put(propertyMeta.getName(), metaColumnProperty);
				}
			}
		}
		return columnNameMap;
	}

	/* get table name
	 *
	 * @param targetClass targetClass
	 * @return tableName
	 * @since 0.0.1
	 */
	public static String getTableName(Class<?> targetClass) {

		if (targetClass.isAnnotationPresent(TableName.class)) {
			return targetClass.getAnnotation(TableName.class).value();
		}
		return null;
	}
}
