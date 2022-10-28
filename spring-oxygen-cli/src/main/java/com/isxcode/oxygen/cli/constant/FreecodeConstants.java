package com.isxcode.oxygen.cli.constant;

import java.util.Arrays;
import java.util.List;

/**
 * freecode constants
 *
 * @author ispong
 * @since 0.0.1
 */
public interface FreecodeConstants {

	/** java file suffix */
	String JAVA_FILE_SUFFIX = ".java";

	/** table name split str */
	String splitStr = ",";

	/** sys columns */
	List<String> sysColumns =
			Arrays.asList(
					"created_date",
					"created_by",
					"last_modified_date",
					"last_modified_by",
					"version",
					"is_delete");
}
