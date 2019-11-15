package com.isxcode.ispring.service.impl;

import com.isxcode.ispring.config.PropertiesConfig;
import com.isxcode.ispring.model.entity.LogEntity;
import com.isxcode.ispring.dao.LogDao;
import com.isxcode.ispring.service.LogService;
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

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Override
    public void test(){
        System.out.println(propertiesConfig.getLocation());
    }

}
