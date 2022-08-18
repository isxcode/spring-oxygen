package com.isxcode.oxygen.flysql.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isxcode.oxygen.flysql.annotation.*;
import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * base entity
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class BaseEntity {

//    @RowId
//    private String id;

    @CreatedDate
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @Version
    private Integer version;

    @IsDelete
    @ColumnName(FlysqlConstants.IS_DELETE_COL)
    private Integer isDelete;
}
