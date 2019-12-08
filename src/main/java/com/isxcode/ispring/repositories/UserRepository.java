package com.isxcode.ispring.repositories;

import com.isxcode.ispring.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * fileEntity
 *
 * 单表操作使用jpa,无非就是添加/修改/删除
 * 多表查询使用mybatis 多表查询,视图查询,查询条件编辑
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/23
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

    /**
     * 更新操作
     *
     * @param nickName 昵称
     * @since 2019-11-30
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "update UserEntity set version=version+1, lastModifiedBy='',lastModifiedDate='', lastName='www' where nickName= ?1 and version=version+1 ")
    void updateUserName(String nickName);




}
