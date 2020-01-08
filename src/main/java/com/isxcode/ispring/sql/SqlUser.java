package com.isxcode.ispring.sql;

import com.isxcode.ispring.common.BaseEntity;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
@Component
public class SqlUser extends BaseEntity {

    @ColumnName("nick_name")
    private String nickName;

    private String firstName;

}


