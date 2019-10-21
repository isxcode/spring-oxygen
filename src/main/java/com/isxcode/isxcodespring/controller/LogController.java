package com.isxcode.isxcodespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isxcode.isxcodespring.service.LogService;
import com.isxcode.isxcodespring.common.BaseController;

/**
 * 日志表 Api
 *
 * @author ispong
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

}
