package com.isxcode.oxygen.cli.command;

import static com.isxcode.oxygen.cli.store.LocalStorage.nowCommandCode;

import com.isxcode.oxygen.cli.store.LocalStorage;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * all command
 *
 * @author ispong
 * @since 0.0.2
 */
@ShellComponent
public class CommandCenter {

	private final CommandService commandService;

	public CommandCenter(CommandService commandService) {

		this.commandService = commandService;
	}

	@ShellMethod(key = "init", value = "init project")
	public String init() {

		if (commandService.canGenerateProject()) {

			nowCommandCode = "Select Build Tool";

			return "" + "select build tool: \n" + "    [A] Gradle \n" + "    [B] Maven ";
		}

		return "please full project info: \n\n"
				+ commandService.printProjectInfo()
				+ "\ncommand like: oxygen set group=xxx  \n";
	}

	@ShellMethod(key = "oxygen", value = "set oxygen params")
	public String oxygen(String opt, String params) {

		if (opt == null) {
			return "command is not valid";
		}

		if ("set".equals(opt)) {
			if (params == null) {
				return "command is not valid";
			}
			String[] split = params.split("=");
			String type = split[0];
			String value = split[1];
			switch (type) {
				case "name":
					LocalStorage.projectInfo.setName(value);
					break;
				case "localPath":
					LocalStorage.localPath = value;
					break;
				case "artifact":
					LocalStorage.projectInfo.setArtifact(value);
					break;
				case "group":
					LocalStorage.projectInfo.setGroup(value);
					break;
				case "packageName":
					LocalStorage.projectInfo.setPackageName(value);
					break;
				case "description":
					LocalStorage.projectInfo.setDescription(value);
					break;
			}

			return "please full project info: \n\n"
					+ commandService.printProjectInfo()
					+ "\n"
					+ "command like: oxygen set group=xxx  \n";
		}

		return "" + "select build tool: \n" + "    [A] gradle \n" + "    [B] maven ";
	}

	@ShellMethod(key = "A", value = "A Choose")
	public String A() {

		switch (nowCommandCode) {
			case "Select Build Tool":
				return "" + "Select Java Version  \n" + "  [A] 15 \n" + "  [B] 11 \n" + "  [C] 8  \n";
		}

		return "";
	}

	@ShellMethod(key = "B", value = "B Choose")
	public String B() {

		return "";
	}

	@ShellMethod(key = "C", value = "C Choose")
	public String C() {

		return "";
	}

	public void Y() {}

	public void N() {}
}
