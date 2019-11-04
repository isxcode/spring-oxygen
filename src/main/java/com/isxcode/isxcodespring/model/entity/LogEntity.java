package com.isxcode.isxcodespring.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.isxcode.isxcodespring.annotation.GenerateType;
import com.isxcode.isxcodespring.utils.GeneratorUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 日志表 Entity
 *
 * @author ispong
 * @since 2019-10-21
 */
@Component
@Data
@Accessors(chain = true)
@TableName("log")
public class LogEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 返回参数
     */
    private String responseParams;

    /**
     * 结束时间
     */
    private LocalDateTime endDate;

    /**
     * 开始时间
     */
    private LocalDateTime startDate;

    /**
     * 对象的uuid
     */
    private String id;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    public LogEntity(){
        this.id = GeneratorUtils.generateUuid();
        this.createBy = GenerateType.SYSTEM.toString();
        this.createDate = LocalDateTime.now();
    }
}
