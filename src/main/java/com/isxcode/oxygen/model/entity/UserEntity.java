package com.isxcode.oxygen.model.entity;

import com.isxcode.oxygen.flysql.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户信息表 Entity
 *
 * @author ispong
 * @since 2019-11-15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Component
@Scope("prototype")
public class UserEntity extends BaseEntity implements Serializable {

    private String firstName;

    private String lastName;

    private String nickName;

    private String accountId;
}
