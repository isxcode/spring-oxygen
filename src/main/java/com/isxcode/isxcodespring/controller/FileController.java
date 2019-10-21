package com.isxcode.isxcodespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isxcode.isxcodespring.service.FileService;
import com.isxcode.isxcodespring.common.BaseController;

/**
 * 文件表 Api
 *
 * @author ispong
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

}
