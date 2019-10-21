package com.isxcode.isxcodespring.service.impl;

import com.isxcode.isxcodespring.moudle.entity.FileEntity;
import com.isxcode.isxcodespring.dao.FileDao;
import com.isxcode.isxcodespring.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件表 service impl
 *
 * @author ispong
 * @since 2019-10-21
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileDao, FileEntity> implements FileService {

    private final FileDao fileDao;

    private final FileEntity fileEntity;

    @Autowired
    public FileServiceImpl(FileDao fileDao,FileEntity fileEntity) {
        this.fileDao = fileDao;
        this.fileEntity = fileEntity;
    }
}
