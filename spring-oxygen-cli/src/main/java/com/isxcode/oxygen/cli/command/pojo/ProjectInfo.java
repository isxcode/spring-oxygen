package com.isxcode.oxygen.cli.command.pojo;

import lombok.Data;

@Data
public class ProjectInfo {

    private String group;

    private String artifact;

    private String name;

    private String description;

    private String packageName;

    private String localPath;

    @Override
    public String toString() {
        return "现在的项目信息配置:  \n" +
            "      [*]localPath: " + localPath + '\n' +
            "      [*]group: " + group + '\n' +
            "      [*]artifact : " + artifact + '\n' +
            "      [*]name : " + name + '\n' +
            "      [*]description : " + description + '\n' +
            "      [*]packageName : " + packageName + '\n';
    }
}
