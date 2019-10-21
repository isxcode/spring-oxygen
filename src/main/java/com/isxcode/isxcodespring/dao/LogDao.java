package com.isxcode.isxcodespring.dao;

import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;

import com.isxcode.isxcodespring.model.entity.LogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 日志表 Dao
 *
 * @author ispong
 * @since 2019-10-21
 */
@Mapper
@Component
public interface LogDao extends BaseMapper<LogEntity> {

}
