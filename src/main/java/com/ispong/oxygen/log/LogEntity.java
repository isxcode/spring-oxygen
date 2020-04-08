package com.ispong.oxygen.log;

import java.lang.Long;

import com.ispong.oxygen.flysql.common.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Data;
import com.ispong.oxygen.flysql.annotation.TableName;
import java.io.Serializable;

/**
 * log entity
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@TableName("log")
@EqualsAndHashCode(callSuper = true)
public class LogEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String logUuid;

    private String apiName;

    private String requestBody;

    private String responseBody;

    private Long executeDate;

    private Long executeTime;

}