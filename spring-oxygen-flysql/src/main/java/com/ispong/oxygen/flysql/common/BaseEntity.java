package com.ispong.oxygen.flysql.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate;

    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy;

    @Version
    @JsonIgnore
    private Integer version;
}
