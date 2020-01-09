package com.isxcode.ispring.code;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 配置freemarker对象信息
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-08
 */
@Data
public class CodeInfo {

    private String filePath;

    private String directoryPath;

    private String templateName;

    private List<TableColumn> tableColumns;

    private String className;

    private String tableComment;

    private String author;

    private String packageName;

    private LocalDateTime date;

    private String tableName;

    private List<String> importPackages;

    private String tableHump;
}
