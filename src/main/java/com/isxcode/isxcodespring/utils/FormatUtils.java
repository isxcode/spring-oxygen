package com.isxcode.isxcodespring.utils;


import com.isxcode.isxcodespring.exception.FileException;

/**
 * 转换类型工具类
 *
 * @author ispong
 * @date 2019/10/17
 * @version v0.1.0
 */
public class FormatUtils {

    /**
     * 转换图片文件格式
     *
     * image/gif   gif图片格式
     * image/jpeg  pg图片格式
     * image/png   png图片格式
     *
     * @param fileName 文件名称
     * @return 图片格式
     * @since 2019/10/17
     */
    public static String formatImageName(String fileName){

        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        switch (fileSuffix) {
            case "gif":
                return "image/gif";
            case "jpeg":
            case "jpg":
                return "image/jpeg";
            case "png":
                return "image/png";
            default:
                throw new FileException("文件的格式无法显示");
        }
    }

}
