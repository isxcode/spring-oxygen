package com.isxcode.ispring.service;

import org.springframework.stereotype.Component;
import com.isxcode.ispring.model.entity.LogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 日志表 Service
 *
 * @author ispong
 * @since 2019-10-21
 */
@Component
public interface LogService extends IService<LogEntity> {

    void test();
}
