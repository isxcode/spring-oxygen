package com.isxcode.ispring.model.entity;


import com.isxcode.ispring.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户表 Entity
 *
 * @author ispong
 * @since 2020-01-13T14:42:16.916104600
 */
@NoArgsConstructor
@Component
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_info")
public class UserInfoEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String account;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String enabledStatus;

}
