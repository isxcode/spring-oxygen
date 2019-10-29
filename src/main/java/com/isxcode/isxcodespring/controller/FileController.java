package com.isxcode.isxcodespring.controller;

import com.isxcode.isxcodespring.common.BaseController;
import com.isxcode.isxcodespring.exception.FileException;
import com.isxcode.isxcodespring.model.dto.FileRequestDto;
import com.isxcode.isxcodespring.model.entity.FileEntity;
import com.isxcode.isxcodespring.repositories.FileRepository;
import com.isxcode.isxcodespring.service.FileService;
import com.isxcode.isxcodespring.utils.ExcelUtils;
import com.isxcode.isxcodespring.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    private final FileRepository fileRepository;

    @Autowired
    public FileController(FileService fileService, FileRepository fileRepository) {

        this.fileRepository = fileRepository;
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
     * @param file 文件的uuid
     * @since 2019/9/30
     */
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {

        return successResponse("上传文件成功", fileService.uploadFile(file));
    }

    /**
     * 删除文件
     *
     * @param fileRequestDto fileRequestDto
     * @since 2019/9/30
     */
    @PostMapping("/delete")
    public ResponseEntity deleteFile(@RequestBody FileRequestDto fileRequestDto) {

        Assert.notNull(fileRequestDto.getFileId(), "fileId 不能为空");
        fileService.deleteFile(fileRequestDto.getFileId());
        return successResponse("删除文件成功", "");
    }

    /**
     * 导出Excel表
     *
     * @since 2019/9/30
     */
    @GetMapping("loadExcel")
    public ResponseEntity<Resource> loadExcel(){

        // 定义文件名转码
        // firefox|chrome|safari|opera
        String fileNameChrome = new String("测试文件".getBytes(), StandardCharsets.ISO_8859_1);
        // IE
        String fileNameIe = URLEncoder.encode("测试文件", StandardCharsets.UTF_8);
        // 获取service层的返回list<DTO>
        List<FileEntity> list = fileService.list();
        // 自定义Excel的表头名
        String[] columns = {"版本id","文件uuid", "文件创建者","文件创建时间","文件名称","文件大小","创建状态"};
        // 使用excel的工具类
        Resource resource = ExcelUtils.generateExcel(columns, list, FileEntity.class);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileNameChrome + ".xlsx" + "\"")
                .body(resource);
    }

}

