//package com.ispong.oxygen.core.freemarker;
//
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
//import java.io.IOException;
//
//public class FreemarkerUtils {
//
//    private static FreeMarkerConfigurer freeMarkerConfigurer;
//
//    public FreemarkerUtils(FreeMarkerConfigurer freeMarkerConfigurer) {
//
//        FreemarkerUtils.freeMarkerConfigurer = freeMarkerConfigurer;
//    }
//
//    public static void generateFile() {
//        try {
//            System.out.println(freeMarkerConfigurer.getConfiguration().getTemplate("test.ftlh"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
