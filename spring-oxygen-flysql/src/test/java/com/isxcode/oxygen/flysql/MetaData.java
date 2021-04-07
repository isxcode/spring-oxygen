package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.annotation.TableName;
import com.isxcode.oxygen.flysql.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("META_DATA_T")
public class MetaData extends BaseEntity {

    private String anString;

    private Date anDate;

    private LocalDate anLocalDate;

    private LocalDateTime anLocalDateTime;

    private BigDecimal anBigDecimal;

    private char anChar;

    private int anInt;

    private double anDouble;

    private long anLong;

    private boolean anBoolean;

    private short anShort;

    private float anFloat;

    private byte anByte;
}
