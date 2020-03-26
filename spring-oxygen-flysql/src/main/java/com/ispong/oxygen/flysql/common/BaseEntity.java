package com.ispong.oxygen.flysql.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ispong.oxygen.flysql.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 自定义基础类型
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class BaseEntity {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @Version
    private Integer version;
}
