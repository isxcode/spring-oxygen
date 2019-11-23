package com.isxcode.ispring.repositories;

import com.isxcode.ispring.model.dto.UserInfoDto;
import com.isxcode.ispring.model.entity.UserEntity;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * fileEntity
 * 单表操作使用jpa,无非就是添加/修改/删除
 * 多表查询使用mybatis 多表查询,视图查询,查询条件编辑
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/23
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {


    /**
     * <p>
     *
     * @param
     * @return
     * @since 2019-11-22
     */
    @Query(value = "" +
            "select user.id userId, account.passwd password\n" +
            "from user\n" +
            "         left join account on user.account_id = account.id\n" +
            "where user.id = :userId", nativeQuery = true)
    Object getCustomUserInfo(@Param("userId") String userId);

}
