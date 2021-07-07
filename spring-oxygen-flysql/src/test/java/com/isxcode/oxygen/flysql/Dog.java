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
 * dog meta date
 *
 * @author ispong
 * @since 0.0.2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("DOGS_T")
public class Dog extends BaseEntity {

    private Integer id;

    private String name;

    private Double amountDouble;

    private BigDecimal amountBigDecimal;

    private Date birthDate;

    private LocalDate birthLocalDate;

    private LocalDateTime birthLocalDateTime;

    private Boolean isAlive;
}
