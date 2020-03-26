package com.ispong.oxygen.module.file;

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.RowId;
import com.ispong.oxygen.flysql.annotation.TableName;
import com.ispong.oxygen.flysql.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("file")
public class FileEntity extends BaseEntity {

    /**
     * 文件的uuid
     */
    @RowId
    @ColumnName("FILE_UUID")
    private String fileId;

    /**
     * 文件名
     */
    @ColumnName("FILE_NAME")
    private String fileName;

    /**
     * 文件的后缀
     */
    @ColumnName("FILE_SUFFIX")
    private String fileSuffix;

    /**
     * 文件的大小(KB)
     */
    @ColumnName("FILE_SIZE")
    private Long fileSize;

    /**
     * 文件的状态
     */
    @ColumnName("FILE_STATUS")
    private String fileStatus;

    /**
     * 业务类型
     */
    @ColumnName("BUSINESS_TYPE")
    private String businessType;

    /**
     * 业务id
     */
    @ColumnName("BUSINESS_ID")
    private String businessId;
}
