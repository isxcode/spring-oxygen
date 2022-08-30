package com.isxcode.oxygen.flysql;

import com.isxcode.oxygen.flysql.annotation.IsDelete;
import com.isxcode.oxygen.flysql.annotation.LastModifiedBy;
import com.isxcode.oxygen.flysql.annotation.LastModifiedDate;
import com.isxcode.oxygen.flysql.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@TableName("DOGS_T")
public class Dog {

    private Integer id;

    private String name;

    private Double amountDouble;

    private BigDecimal amountBigDecimal;

    private Date birthDate;

    private LocalDate birthLocalDate;

    private LocalDateTime birthLocalDateTime;

    private Boolean isAlive;

    @IsDelete
    private Integer isDelete;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Dog(Integer id, String name, Double amountDouble, BigDecimal amountBigDecimal, Date birthDate, LocalDate birthLocalDate, LocalDateTime birthLocalDateTime, Boolean isAlive) {
        this.id = id;
        this.name = name;
        this.amountDouble = amountDouble;
        this.amountBigDecimal = amountBigDecimal;
        this.birthDate = birthDate;
        this.birthLocalDate = birthLocalDate;
        this.birthLocalDateTime = birthLocalDateTime;
        this.isAlive = isAlive;
    }
}
