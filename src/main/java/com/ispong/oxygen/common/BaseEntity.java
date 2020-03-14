package com.ispong.oxygen.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private String uuid;

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime lastModifiedDate;

    private String lastModifiedBy;

    private Integer version;
}
