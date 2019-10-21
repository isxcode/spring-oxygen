package com.isxcode.isxcodespring.annotation;

/**
 * 注解参数- Generate注解
 *
 * @author ispong
 * @date 2019/10/8
 * @version v0.1.0
 */
public enum GenerateType {

    /**
     * 普通uuid
     */
    UUID,

    /**
     * 当前时间
     */
    DATETIME,

    /**
     * 默认创建者
     */
    SYSTEM;

    GenerateType() { }
}
