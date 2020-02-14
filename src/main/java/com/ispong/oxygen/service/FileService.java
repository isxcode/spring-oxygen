//package com.isxcode.oxygen.service;
//
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//import com.isxcode.oxygen.model.entity.FileEntity;
//import com.baomidou.mybatisplus.extension.service.IService;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * 文件表 Service
// *
// * @author ispong
// * @since 2019-10-21
// */
//@Component
//public interface FileService extends IService<FileEntity> {
//
//    /**
//     * 文件上传
//     *
//     * @param file 上传文件
//     * @return String fileId
//     * @since 2019/9/30
//     */
//    String uploadFile(MultipartFile file);
//
//    /**
//     * 文件下载
//     *
//     * @param fileId 文件uuid
//     * @return Resource 文件源
//     * @since 2019/10/16
//     */
//    Resource loadFile(String fileId);
//
//    /**
//     * 文件真删除
//     *
//     * @param fileId 文件uuid
//     * @since 2019/10/16
//     */
//    void deleteFile(String fileId);
//
//    /**
//     * 获取文件名
//     *
//     * @param fileId 文件uuid
//     * @return file DO
//     * @since 2019/10/16
//     */
//    FileEntity getFileEntity(String fileId);
//
//
//
//}
