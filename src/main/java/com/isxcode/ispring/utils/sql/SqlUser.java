package com.isxcode.ispring.utils.sql;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.isxcode.ispring.common.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Columns;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

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


