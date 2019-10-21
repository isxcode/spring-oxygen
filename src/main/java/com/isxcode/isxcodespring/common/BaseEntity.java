package com.isxcode.isxcodespring.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.isxcode.isxcodespring.annotation.GenerateType;
import com.isxcode.isxcodespring.utils.GeneratorUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * BaseEntity
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/8
 */
@Data
@Component
public class BaseEntity {

    /**
     * 对象的uuid
     */
    @TableField("id")
    private String id;

    /**
     * 创建者
     */
    @TableField("createBy")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("createDate")
    private LocalDateTime createDate;

    public BaseEntity(){
        this.id = GeneratorUtils.generateUuid();
        this.createBy = GenerateType.SYSTEM.toString();
        this.createDate = LocalDateTime.now();
    }
}