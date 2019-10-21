package com.isxcode.isxcodespring.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.isxcode.isxcodespring.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 文件表 Entity
 *
 * @author ispong
 * @since 2019-10-21
 */
@NoArgsConstructor
@Component
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("file")
public class FileEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件的名字
     */
    private String fileName;

    /**
     * 文件的大小
     */
    private String fileSize;

    /**
     * 文件状态
     */
    private String fileStatus;


}
