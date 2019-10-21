package com.isxcode.isxcodespring.controller;

import com.isxcode.isxcodespring.exception.FileException;
import com.isxcode.isxcodespring.moudle.entity.FileEntity;
import com.isxcode.isxcodespring.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isxcode.isxcodespring.service.FileService;
import com.isxcode.isxcodespring.common.BaseController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表 Api
 *
 * @author ispong
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 文件显示/文件下载
     *
     * http://localhost:8080/showFile/fileId
     * http://localhost:8080/download/fileId
     *
     * @param fileId 文件uuid
     * @param type download type
     * @since 2019/9/30
     */
    @GetMapping("/{type:.+}/{fileId:.+}")
    public ResponseEntity<Resource> showFile(@PathVariable String fileId, @PathVariable String type){

        Resource file = fileService.loadFile(fileId);
        FileEntity fileEntity = fileService.getFileEntity(fileId);

        switch (type){
            case "download":
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                        .body(file);
            case "showFile":
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, FormatUtils.formatImageName(fileEntity.getFileName()))
                        .body(file);
            default:
                throw new FileException("file has problem");
        }

    }

    /**
     * 上传文件
     *
     * @param file 上传的文件
     * @since 2019/9/30
     */
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {

        return successResponse("上传文件成功", fileService.uploadFile(file));
    }



}

