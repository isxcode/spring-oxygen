package com.isxcode.isxcodespring.service.impl;

import com.isxcode.isxcodespring.model.entity.LogEntity;
import com.isxcode.isxcodespring.dao.LogDao;
import com.isxcode.isxcodespring.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志表 service impl
 *
 * @author ispong
 * @since 2019-10-21
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    private final LogDao logDao;

    private final LogEntity logEntity;

    @Autowired
    public LogServiceImpl(LogDao logDao,LogEntity logEntity) {
        this.logDao = logDao;
        this.logEntity = logEntity;
    }
}
