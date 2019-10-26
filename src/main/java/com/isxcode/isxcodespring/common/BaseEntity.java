package com.isxcode.isxcodespring.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.isxcode.isxcodespring.annotation.GenerateType;
import com.isxcode.isxcodespring.utils.GeneratorUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * BaseEntity
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/8
 */
@Data
public class BaseEntity {

    /**
     * 对象的uuid
     */
    @TableField("id")
    @Id
    @GeneratedValue
    private String id;

    /**
     * 创建者
     */
    @TableField("create_by")
    @Column(nullable = false)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_date")
    @Column(nullable = false)
    private LocalDateTime createDate;

    public BaseEntity(){
        this.id = GeneratorUtils.generateUuid();
        this.createBy = GenerateType.SYSTEM.toString();
        this.createDate = LocalDateTime.now();
    }
}