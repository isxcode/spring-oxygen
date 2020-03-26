package com.ispong.oxygen.module.log;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.TableName;
import com.ispong.oxygen.flysql.common.BaseEntity;
import com.ispong.oxygen.flysql.common.LocalDateTimeDeserializer;
import com.ispong.oxygen.flysql.common.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 日志对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Component
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("log")
public class LogEntity extends BaseEntity {

    @ColumnName("log_uuid")
    private String logId;

    @ColumnName("api_name")
    private String apiName;

    @ColumnName("module_name")
    private String moduleName;

    @ColumnName("request_body")
    private String requestBody;

    @ColumnName("response_body")
    private String responseBody;

    @ColumnName("execute_time")
    private Long executeTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ColumnName("execute_date")
    private LocalDateTime executeDate;
}
