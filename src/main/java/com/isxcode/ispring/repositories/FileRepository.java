package com.isxcode.ispring.repositories;

import com.isxcode.ispring.model.entity.FileEntity;
import org.springframework.data.repository.Repository;

/**
 * fileEntity
 * 单表操作使用jpa,无非就是添加/修改/删除
 * 多表查询使用mybatis 多表查询,视图查询,查询条件编辑
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/23
 */
public interface FileRepository extends Repository<FileEntity, String>{




}
