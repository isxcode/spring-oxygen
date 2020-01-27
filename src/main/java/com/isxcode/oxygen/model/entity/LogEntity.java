package com.isxcode.oxygen.model.entity;

import com.isxcode.oxygen.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志表 Entity
 *
 * @author ispong
 * @since 2019-11-15
 */
@NoArgsConstructor
@Component
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "log")
public class LogEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 返回参数
     */
    private String responseParams;

    /**
     * 开始时间
     */
    private LocalDateTime startDate;

    /**
     * 执行时间
     */
    private long executeTime;

}
