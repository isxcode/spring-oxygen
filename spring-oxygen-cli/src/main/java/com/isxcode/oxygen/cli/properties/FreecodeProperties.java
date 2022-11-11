package com.isxcode.oxygen.cli.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * freecode properties
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@ConfigurationProperties("oxygen.freecode")
public class FreecodeProperties {

	/** file type */
	private List<String> fileTypes =
			Arrays.asList("controller", "entity", "service", "repository", "innerService");

	/** ignore columns */
	private List<String> ignoreColumns = new ArrayList<>();

	/** module path (src/main/java/com) end must not has / */
	private String modulePath;

	/** author */
	private String author = "anonymous";

	/** version */
	private String version = "v0.0.1";

	/** base entity */
	private String baseEntityClass = "com.isxcode.oxygen.flysql.common.BaseEntity";

	/** table prefix */
	private String tablePrefix;

	/** template prefix */
	private String templatePrefix = ".java.ftl";
}
