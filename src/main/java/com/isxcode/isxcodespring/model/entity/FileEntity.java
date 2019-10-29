package com.isxcode.isxcodespring.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.isxcode.isxcodespring.annotation.Generate;
import com.isxcode.isxcodespring.annotation.GenerateType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件表 Entity
 *
 * @author ispong
 * @since 2019-10-21
 */
@NoArgsConstructor
@Component
@Data
@Accessors(chain = true)
@TableName("file")
@Entity
@Table(name = "file")
public class FileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对象的uuid
     */
    @Id
    @TableField("id")
    @GeneratedValue
    @Generate(type = GenerateType.UUID)
    private String id;

    /**
     * 创建者
     */
    @TableField("create_by")
    @Column(nullable = false)
    @Generate(type = GenerateType.SYSTEM)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_date")
    @Column(nullable = false)
    @Generate(type = GenerateType.DATETIME)
    private LocalDateTime createDate;

    /**
     * 文件的名字
     */
    @Column(nullable = false)
    private String fileName;

    /**
     * 文件的大小
     */
    @Column(nullable = false)
    private String fileSize;

    /**
     * 文件状态
     */
    @Column(nullable = false)
    private String fileStatus;

}
