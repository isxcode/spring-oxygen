package com.isxcode.oxygen.cli.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CommandCenter {

    @ShellMethod(key = "init", value = "init project")
    public String init() {

        if (LocalStorage.projectInfo.getArtifact() == null ||
            LocalStorage.projectInfo.getDescription() == null ||
            LocalStorage.projectInfo.getGroup() == null ||
            LocalStorage.projectInfo.getName() == null ||
            LocalStorage.projectInfo.getPackageName() == null ||
            LocalStorage.projectInfo.getLocalPath() == null) {

            return "请先配置好项目信息 \n" +
                LocalStorage.projectInfo.toString() +
                "command like: oxygen set name=xxx  \n";
        }

        return "" +
            "select build tool: \n" +
            "    [A] gradle \n" +
            "    [B] maven ";
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
                    LocalStorage.projectInfo.setLocalPath(value);
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

            return "请先配置好项目信息 \n" +
                LocalStorage.projectInfo.toString();
        }

        return "" +
            "select build tool: \n" +
            "    [A] gradle \n" +
            "    [B] maven ";
    }

    public void A(){

    }



    public void B() {

    }



    public void Y(){

    }



    public void N() {

    }



}
