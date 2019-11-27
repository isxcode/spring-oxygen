package com.isxcode.ispring.repositories;

import com.isxcode.ispring.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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

//    /**
//     * <p>
//     *
//     * @param userId
//     * @return
//     * @since 2019-11-22
//     */
//    @Select("select usr.id id, ac.passwd passwd\\n\" +\n" +
//            "            \"from user usr\\n\" +\n" +
//            "            \"  left join account ac on usr.account_id = ac.id\\n\" +\n" +
//            "            \"where usr.id = ?1")
//    UserInfoDto findByAccountId(String userId);


}
