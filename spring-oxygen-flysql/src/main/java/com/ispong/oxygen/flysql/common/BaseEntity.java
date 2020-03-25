package com.ispong.oxygen.flysql.common;

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

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime lastModifiedDate;

    private String lastModifiedBy;

    private Integer version;
}
