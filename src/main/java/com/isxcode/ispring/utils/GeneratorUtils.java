package com.isxcode.ispring.utils;

import com.isxcode.ispring.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 初始化工具
 *
 * @author ispong
 * @date 2019/10/20
 * @version v0.1.0
 */
public class GeneratorUtils {

    /**
     * 初始化32uuid
     *
     * @return 32位uuid
     * @since 2019/10/20
     */
    public static String generateUuid(){

        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 初始化BaseEntity
     *
     * @param baseEntity Entity对象
     * @since 2019-11-16
     */
    public static <A extends BaseEntity> void generateEntity(A baseEntity) {

        baseEntity.setId(GeneratorUtils.generateUuid());
        baseEntity.setCreateDate(LocalDateTime.now());
        baseEntity.setCreateBy("ispong");
    }
}
