package com.isxcode.oxygen.cli.command;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("subCommands")
public class SubCommand {

	@ShellMethod(key = "fun1", value = "This one is in 'Other Commands'")
	public String fun1() {

		return "fun1";
	}

	@ShellMethod(key = "fun2", value = "And this one is 'Yet Another Group'")
	public String fun2() {

		return "fun2";
	}
}
