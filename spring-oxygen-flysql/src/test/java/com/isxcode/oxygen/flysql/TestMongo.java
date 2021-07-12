//package com.isxcode.oxygen.flysql;
//
//import com.isxcode.oxygen.flysql.config.FlysqlAutoConfiguration;
//import com.isxcode.oxygen.flysql.core.Flysql;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@DataMongoTest
//@ContextConfiguration(classes = {FlysqlAutoConfiguration.class})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("mongo")
//public class TestMongo {
//
//    private final Flysql flysql;
//
//    public TestMongo(@Qualifier("flysqlFactory") Flysql flysql) {
//
//        this.flysql = flysql;
//    }
//
//    @Test
//    public void testMongo() {
//
//        Dog dog1 = null;
//        Dog dog2 = null;
//        Dog dog3 = null;
//
//        ArrayList<Dog> dogList = new ArrayList<>();
//        try {
//            dog1 = new Dog(1, "jack", 1.1, new BigDecimal("1.1"), new Date(), LocalDate.now(), LocalDateTime.now(), true);
//            dog2 = new Dog(2, "john", 1.2, new BigDecimal("1.2"), new Date(), LocalDate.now(), LocalDateTime.now(), true);
//            dog3 = new Dog(3, "rose", 1.3, new BigDecimal("1.3"), new Date(), LocalDate.now(), LocalDateTime.now(), true);
//        } catch (NumberFormatException ignored) {
//        }
//
//        dogList.add(dog2);
//        dogList.add(dog3);
////
////        // 查询所有数据
////        List<Dog> metaDataList = flysql.buildMongo().select(Dog.class).query();
////        metaDataList.forEach(System.out::println);
////        System.out.println("===============================================================");
////
////        // 查询单条数据
////        MetaData metaData = flysql.buildMongo().select(Dog.class).eq("anString", "data1").getOne();
////        System.out.println(metaData);
////        System.out.println("===============================================================");
////
////        // 条件删除数据
////        flysql.buildMongo().delete(MetaData.class).eq("anString", "data1").doDelete();
////        metaDataList = flysql.buildMongo().select(MetaData.class).query();
////        metaDataList.forEach(System.out::println);
////        System.out.println("===============================================================");
////
////        // 单条更新
////        flysql.buildMongo().update(MetaData.class).eq("anString", "data3")
////            .update("anInt", "4")
////            .update("anDouble", "4.4")
////            .update("anDate", new Date())
////            .doUpdate();
////        metaDataList = flysql.buildMongo().select(MetaData.class).query();
////        metaDataList.forEach(System.out::println);
////        System.out.println("===============================================================");
//    }
//}
