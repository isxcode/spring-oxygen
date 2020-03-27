package com.ispong.oxygen.module.file;

import com.ispong.oxygen.constants.OxygenConstants;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * 文件业务服务
 *
 * @author ispong
 * @since 0.0.1
 */
@Service
public class FileService {

    private final FileRepository fileRepository;

    private final Path rootLocation;

    public FileService(FileRepository fileRepository, FileProperties fileProperties) {

        this.fileRepository = fileRepository;
        this.rootLocation = Paths.get(fileProperties.getLocation());
    }

    public String uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
            throw new FileException("无法储存空文件");
        }

        // 封装上传对象
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileId(UUID.randomUUID().toString());
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
        fileName = fileName.substring(0, fileName.length() - suffix.length() - 1);
        fileEntity.setFileName(fileName);
        fileEntity.setFileSuffix(suffix);
        fileEntity.setFileSize(file.getSize());
        fileEntity.setFileStatus(OxygenConstants.ENABLE);
        fileRepository.saveFile(fileEntity);

        // 上传文件
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(fileEntity.getFileId()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileException("上传文件失败");
        }
        return fileEntity.getFileId();
    }

    /**
     * 加载文件
     *
     * @param fileId 文件id
     * @return 文件资源
     * @since 0.0.1
     */
    public Resource loadFile(String fileId) {

        try {
            Resource resource = new UrlResource(rootLocation.resolve(fileId).toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileException("Could not read file: " + fileId);
            }
        } catch (MalformedURLException e) {
            throw new FileException("Could not read file: " + fileId, e);
        }
    }

    /**
     * 删除文件
     *
     * @param fileId fileUuid
     * @since 0.0.1
     */
    public void deleteFile(String fileId) {

        fileRepository.deleteFile(fileId);

        try {
            Path file = rootLocation.resolve(fileId);
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException e) {
            throw new FileException("Could not read file: " + fileId, e);
        }
    }

    /**
     * 获取文件信息
     *
     * @param fileId 文件id
     * @return 文件信息
     * @since 0.0.1
     */
    public FileEntity getFileEntity(String fileId) {

        return fileRepository.getFile(fileId);
    }
}
