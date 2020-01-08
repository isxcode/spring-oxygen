package com.isxcode.ispring.code;

import lombok.Data;

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

}
