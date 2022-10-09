package com.isxcode.oxygen.cli.store;

import com.isxcode.oxygen.cli.pojo.ProjectInfo;

/**
 * cache project info
 *
 * @author ispong
 * @since 0.0.2
 */
public class LocalStorage {

	/** project basic info */
	public static ProjectInfo projectInfo = new ProjectInfo();

	/** now command code to show which command */
	public static String nowCommandCode = "INIT";

	/** project local path */
	public static String localPath = null;
}
