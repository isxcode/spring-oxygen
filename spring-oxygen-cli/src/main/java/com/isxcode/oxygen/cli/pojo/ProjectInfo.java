package com.isxcode.oxygen.cli.pojo;

import lombok.Data;

/**
 * project basic info
 *
 * @author ispong
 * @since 0.0.2
 */
@Data
public class ProjectInfo {

    private String group;

    private String artifact;

    private String name;

    private String description;

    private String packageName;
}
