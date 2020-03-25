package com.ispong.oxygen.module.file;

import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.flysql.common.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * file controller
 *
 * @author ispong
 * @since 0.0.1
 */
@ResponseBody
@RequestMapping("file")
public class FileController extends BaseController {

    private final FileService fileService;

    public FileController(FileService fileService) {

        this.fileService = fileService;
    }

    /**
     * 文件下载
     * http://localhost:port/file/download/fileId
     *
     * @param fileId fileId
     * @since 2019/9/30
     */
    @GetMapping("/download/{fileId:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileId) {

        // 获取文件本身
        Resource file = fileService.loadFile(fileId);
        // 获取文件信心
        FileEntity fileEntity = fileService.getFileEntity(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .body(file);
    }

    /**
     * 文件显示
     * http://localhost:port/show/fileId
     *
     * @param fileId fileId
     * @since 2019/9/30
     */
    @GetMapping("/show/{fileId:.+}")
    public ResponseEntity<Resource> showFile(@PathVariable String fileId) {

        Resource file = fileService.loadFile(fileId);
        FileEntity fileEntity = fileService.getFileEntity(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/" + fileEntity.getFileName().substring(fileEntity.getFileName().lastIndexOf(".") + 1))
                .body(file);
    }

    /**
     * 上传文件
     *
     * @param file 文件的uuid
     * @return 文件id
     * @since 2019/9/30
     */
    @PostMapping("/upload")
    public ResponseEntity<BaseResponse<String>> uploadFile(@RequestParam("file") MultipartFile file) {

        String fileId = fileService.uploadFile(file);
        return successResponse("上传成功", fileId);
    }

    /**
     * 删除文件
     *
     * @param fileId 文件id
     * @since 2019/9/30
     */
    @PostMapping("/delete/{fileId:.+}")
    public ResponseEntity<BaseResponse<String>> deleteFile(@PathVariable String fileId) {

        fileService.deleteFile(fileId);
        return successResponse("删除文件成功", "");
    }
}
