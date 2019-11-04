package com.isxcode.isxcodespring.controller;

import com.isxcode.isxcodespring.common.BaseController;
import com.isxcode.isxcodespring.exception.FileException;
import com.isxcode.isxcodespring.model.dto.FileRequestDto;
import com.isxcode.isxcodespring.model.entity.FileEntity;
import com.isxcode.isxcodespring.repositories.FileRepository;
import com.isxcode.isxcodespring.service.FileService;
import com.isxcode.isxcodespring.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 文件表 Api
 *
 * jpa是否可以多表查询
 *
 * @author ispong
 * @since 2019-10-21
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    /**
     * 负责多表查询
     */
    private final FileService fileService;

    /**
     * 负责单表操作
     */
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
                        .header(HttpHeaders.CONTENT_TYPE, "image/" + fileEntity.getFileName().substring(fileEntity.getFileName().lastIndexOf(".") + 1))
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
    @RequestMapping("loadExcel")
    public void loadExcel(HttpServletResponse response) {

        // 获取文件名
        String fileName = URLEncoder.encode("测试文件", StandardCharsets.UTF_8);
        // 获取数据
        List<FileEntity> data = fileService.list();
        // 生成Excel文件
        XSSFWorkbook workBook = ExcelUtils.generateExcel(data);
        // 设置返回流
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + ".xlsx");
        // 写入返回流
        try (OutputStream outputStream = response.getOutputStream()) {
            workBook.write(outputStream);
        } catch (IOException e) {
            log.info("无法获取返回流");
        }

    }

}

