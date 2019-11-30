package com.isxcode.ispring.model.entity;

import com.isxcode.ispring.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class UserEntity extends BaseEntity implements Serializable {

    private String firstName;

    private String lastName;

    private String nickName;

    private String accountId;
}
