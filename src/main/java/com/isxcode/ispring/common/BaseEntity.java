package com.isxcode.ispring.common;

import com.isxcode.ispring.utils.GeneratorUtils;
import lombok.Data;
import org.springframework.data.annotation.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseEntity
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/8
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对象的uuid
     */
    @Id
    private String id;

    /**
     * 创建者
     */
    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
//    @LastModifiedBy
//    @LastModifiedDate
//    @Version
    @CreatedDate
    private LocalDateTime createDate;


    /**
     * baseEntity 初始化
     *
     * @since 2019-11-16
     */
    public BaseEntity() {

        this.setId(GeneratorUtils.generateUuid());
        this.setCreateDate(LocalDateTime.now());
        this.setCreateBy("ispong");
    }
}