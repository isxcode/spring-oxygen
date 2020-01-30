/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isxcode.oxygen.flysql.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isxcode.oxygen.flysql.ColumnName;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对象的uuid
     */
    @Id
    @GeneratedValue(generator = "uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "com.isxcode.oxygen.flysql.common.BaseUuidGenerator")
    private String uuid;

    /**
     * 创建者
     */
    @JsonIgnore
    @CreatedBy
    @ColumnName("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonIgnore
    @CreatedDate
    @ColumnName("created_date")
    private LocalDateTime createdDate;

    /**
     * 更新者
     */
    @JsonIgnore
    @LastModifiedBy
    @ColumnName("last_modified_by")
    private String lastModifiedBy;

    /**
     * 更新时间
     */
    @JsonIgnore
    @LastModifiedDate
    @ColumnName("last_modified_date")
    private LocalDateTime lastModifiedDate;

    /**
     * 版本号
     */
    @JsonIgnore
    @Version
    @ColumnName("version")
    private Integer version;
}