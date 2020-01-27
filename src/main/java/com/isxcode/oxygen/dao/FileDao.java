package com.isxcode.oxygen.dao;

import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;

import com.isxcode.oxygen.model.entity.FileEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 文件表 Dao
 *
 * @author ispong
 * @since 2019-10-21
 */
@Mapper
@Component
public interface FileDao extends BaseMapper<FileEntity> {

}
