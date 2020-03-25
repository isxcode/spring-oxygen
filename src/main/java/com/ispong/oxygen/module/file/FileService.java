package com.ispong.oxygen.module.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 文件业务服务
 *
 * @author ispong
 * @since 0.0.1
 */
public class FileService {

    private final FileRepository fileRepository;

    private final Path rootLocation;

    public FileService(FileRepository fileRepository, FileProperties fileProperties) {

        this.fileRepository = fileRepository;
        this.rootLocation = Paths.get(fileProperties.getLocation());
    }

    public String uploadFile(MultipartFile file) {

        // 判断文件不为空
        if (file == null || file.isEmpty()) {
            throw new FileException("无法储存空文件");
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setFileStatus("1");
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
