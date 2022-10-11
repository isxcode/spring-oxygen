package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.command.CommandUtils;
import com.isxcode.oxygen.core.file.FileUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class CommandUtilsTests {

    @Test
	@SneakyThrows
	public void testCommand() {

		System.out.println(CommandUtils.executeBack("dir"));

		System.out.println(CommandUtils.execute("dir"));

        System.out.println(CommandUtils.execute("dir", "command.log"));

        FileUtils.recursionDeleteFile("command.log");
    }
}
