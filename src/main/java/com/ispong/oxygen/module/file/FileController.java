package com.ispong.oxygen.module.file;

import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.flysql.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "文件模块")
@RestController
@RequestMapping("file")
public class FileController extends BaseController {

    private final FileService fileService;

    public FileController(FileService fileService) {

        this.fileService = fileService;
    }

    /**
     * 上传文件
     *
     * @param file 文件的uuid
     * @return 文件id
     * @since 2019/9/30
     */
    @ApiOperation("上传文件接口")
    @PostMapping("/upload")
    public ResponseEntity<BaseResponse<String>> uploadFile(@RequestParam("file") @ApiParam("上传的文件") MultipartFile file) {

        String fileId = fileService.uploadFile(file);
        return successResponse("上传成功", fileId);
    }

    /**
     * 文件下载
     * http://localhost:port/file/download/fileId
     *
     * @param fileId fileId
     * @since 2019/9/30
     */
    @ApiOperation("文件下载接口")
    @GetMapping("/download/{fileId:.+}")
    public ResponseEntity<Resource> download(@PathVariable @ApiParam("文件uuid") String fileId) {

        Resource file = fileService.loadFile(fileId);
        FileEntity fileEntity = fileService.getFileEntity(fileId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "." + fileEntity.getFileSuffix() + "\"")
                .body(file);
    }

    /**
     * 文件显示
     * http://localhost:port/file/show/fileId
     *
     * @param fileId fileId
     * @since 2019/9/30
     */
    @ApiOperation("文件预览接口")
    @GetMapping("/show/{fileId:.+}")
    public ResponseEntity<Resource> showFile(@PathVariable @ApiParam("文件uuid") String fileId) {

        Resource file = fileService.loadFile(fileId);
        FileEntity fileEntity = fileService.getFileEntity(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/" + fileEntity.getFileSuffix())
                .body(file);
    }


    /**
     * 删除文件
     *
     * @param fileId 文件id
     * @since 2019/9/30
     */
    @ApiOperation("删除文件接口")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse<String>> deleteFile(@RequestParam @ApiParam("文件uuid") String fileId) {

        fileService.deleteFile(fileId);
        return successResponse("删除文件成功", "");
    }
}
