package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.annotation.TableName;
import com.isxcode.oxygen.flysql.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 测试各种字段类型
 *
 * @author ispong
 * @since 0.0.2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("META_DATA_T")
public class MetaData extends BaseEntity {

    private String id;

    private int aInt;

    private Integer anInt;

    private double aDouble;

    private Double anDouble;

    private long aLong;

    private Long anLong;

    private boolean aBoolean;

    private Boolean anBoolean;

    private short aShort;

    private Short anShort;

    private float aFloat;

    private Float anFloat;

//    oracle 不支持  h2 支持
//    private byte aByte;

//    oracle 不支持  h2 支持
//    private Byte anByte;

    private String aChar;

    private String anString;

    private Date aDate;

    private LocalDate anLocalDate;

    private LocalDateTime anLocalDateTime;

    private BigDecimal anBigDecimal;
}
