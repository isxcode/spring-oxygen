package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.command.CommandUtils;
import org.junit.jupiter.api.Test;

public class CommandUtilsTests {

	@Test
	public void testCommand() {

		System.out.println(CommandUtils.executeBack("dir"));

		System.out.println(CommandUtils.execute("dir", "command.log"));

		System.out.println(CommandUtils.execute("dir"));
	}
}
