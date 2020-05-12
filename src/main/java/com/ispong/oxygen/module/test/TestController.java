package com.ispong.oxygen.module.test;

import com.ispong.oxygen.exception.OxygenException;
import com.ispong.oxygen.module.test.excel.Dog;
import com.ispong.oxygen.utils.excel.ExcelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 测试类中心
 *
 * @author ispong
 * @since 0.0.1
 */
@RestController
@RequestMapping("test")
public class TestController {

    /**
     * 解析excel文件,并储存数据
     *
     * @param file excel文件
     * @since 0.0.1
     */
    @PostMapping("/parseExcel")
    public List<Dog> parseExcel(@RequestParam("file") MultipartFile file) {

        try {
            List<Dog> dogList = ExcelUtils.parseExcel(file.getInputStream(), Dog.class);
            return dogList;
        } catch (IOException e) {
            throw new OxygenException("[excelUtils] 文件读取失败");
        }

    }

//    /**
//     * 取出数据 并导出excel文件
//     *
//     * @param response 响应体
//     * @since 0.0.1
//     */
//    @PostMapping("/generateExcel")
//    public void generateExcel(HttpServletResponse response) {
//
//        try {
//            List<Dog> dogList = ExcelUtils.parseExcel(file.getInputStream(), Dog.class);
//            return dogList;
//        } catch (IOException e) {
//            throw new OxygenException("[excelUtils] 文件读取失败");
//        }
//    }
//
//
//        try(
//    OutputStream fileOut = response.getOutputStream())
//
//    {
//        excelStream.write(fileOut);
//    } catch(
//    IOException e)
//
//    {
//        throw new BizException(BizExceptionEnum.FILE_READ_FAILED);
//    }
//}

}