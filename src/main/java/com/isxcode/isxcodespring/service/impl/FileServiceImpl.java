package com.isxcode.isxcodespring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isxcode.isxcodespring.exception.FileException;
import com.isxcode.isxcodespring.moudle.entity.FileEntity;
import com.isxcode.isxcodespring.dao.FileDao;
import com.isxcode.isxcodespring.moudle.properties.IsxcodeProperties;
import com.isxcode.isxcodespring.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    private final Path rootLocation;

    @Autowired
    public FileServiceImpl(FileDao fileDao, FileEntity fileEntity, IsxcodeProperties isxcodeProperties) {
        this.fileDao = fileDao;
        this.fileEntity = fileEntity;
        this.rootLocation = Paths.get(isxcodeProperties.getLocation());
    }

    @Override
    public String uploadFile(MultipartFile file){

        // 判断文件不为空
        if (file.isEmpty()) {
            throw new FileException("无法储存空文件");
        }

        fileEntity.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setFileSize(String.valueOf(file.getSize()));
        fileEntity.setFileStatus("1");
        save(fileEntity);

        // 上传文件
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(fileEntity.getId()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileException("上传文件失败");
        }

        return fileEntity.getId();
    }

    @Override
    public Resource loadFile(String fileId) {

        Path file = rootLocation.resolve(fileId);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileException("Could not read file: " + fileId);
            }
        } catch (MalformedURLException e) {
            throw new FileException("Could not read file: " + fileId, e);
        }
    }

    @Override
    public void deleteFile(String fileId) {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public FileEntity getFileEntity(String fileId) {

        QueryWrapper<FileEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", fileId);
        FileEntity fileEntity = fileDao.selectOne(queryWrapper);
        Assert.notNull(fileEntity,"此文件不存在");
        return fileEntity;
    }
}
