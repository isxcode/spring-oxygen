package com.isxcode.oxygen.flysql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 排序
 *
 * @author ispong
 * @since 0.0.1
 */
@AllArgsConstructor
public enum OrderType {

	/** 降序 */
	DESC("desc"),

	/** 升序 */
	ASC("asc"),
	;

	@Getter private final String orderType;
}
