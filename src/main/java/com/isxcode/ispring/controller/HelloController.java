package com.isxcode.ispring.controller;

import com.isxcode.ispring.annotation.log.Logs;
import com.isxcode.ispring.common.BaseController;
import com.isxcode.ispring.common.BaseResponse;
import com.isxcode.ispring.model.dto.UserDto;
import com.isxcode.ispring.model.entity.UserEntity;
import com.isxcode.ispring.properties.FreemarkerProperties;
//import io.netty.handler.codec.base64.Base64Encoder;
import com.isxcode.ispring.repositories.UserRepository;
import com.isxcode.ispring.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * spring项目测试类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-11
 */
@Slf4j
@Logs
@RestController
@RequestMapping("hello")
public class HelloController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntity userEntity;

    /**
     * 测试项目启动
     *
     * @since 2019-11-15
     */
    @GetMapping("/test")
    public ResponseEntity<BaseResponse> test() {

        // 保存DO对象

        // 对象更新
        // DTO 转 DO 通过save() 更新

        // 单字段更新
        // 使用mod更新  如何处理version字段

        //

        // 带条件查询所有
//        UserEntity entity = userRepository.findAll().get(0);
//        entity.setNickName("ispong");
//        userRepository.save(entity);
//
//
//
//        List<UserEntity> all = userRepository.findAll();
//
//        // 部分更新  // 全部更新  // 条件更新
//        // 条件删除
//        // 插入已经解决
//        // 主要是DTO查询
//
//        userRepository.saveAll(all);
//        // 现在有个需求 更新所有人的token 为1
//        //
//
//        userRepository.updateUserName("ispong2");
        
        return successResponse("项目启动成功", Calendar.getInstance().getTime().toString());
    }

    /**
     * 测试jpa添加数据接口
     * <p>
     *
     * @param userDto 用户请求DTO
     * @since 2019-12-06
     */
    public ResponseEntity<BaseResponse> testSave(@RequestBody UserDto userDto) {

        // 复制
        BeanUtils.copyProperties(userDto, userEntity);
        // 插入
        userRepository.save(userEntity);
        // 返回
        return successResponse("添加数据成功", "");
    }

    /**
     * 测试jpa批量添加数据接口
     * <p>
     *
     * @param userDto 用户请求DTO
     * @since 2019-12-06
     */
    public ResponseEntity<BaseResponse> testSaveBatch(@RequestBody List<UserDto> userDto) {

        // 批量插入对象
        // 自动添加 系统字段

        List<Object> objects = new ArrayList<>();
//        for(){
//            BeanUtils.copyProperties(userDto, userEntity);
//        }
//        userRepository.saveAll(objects.iterator());

        return successResponse("添加数据成功", "");
    }

    /**
     * 测试jpa单字段更新添加数据接口
     * <p>
     *
     * @param userDto 用户请求DTO
     * @since 2019-12-06
     */
    public ResponseEntity<BaseResponse> testUpdate(@RequestBody UserDto userDto) {


        return successResponse("添加数据成功", "");
    }

    /**
     * 对字段更新
     * <p>
     *
     * @param userDto 用户请求DTO
     * @since 2019-12-06
     */
    public ResponseEntity<BaseResponse> testUpdateAll(@RequestBody UserDto userDto) {


        return successResponse("添加数据成功", "");
    }

    /**
     * 批量更新
     *
     * @param userDto 用户请求DTO
     * @since 2019-12-06
     */
    public ResponseEntity<BaseResponse> testUpdateBatch(@RequestBody UserDto userDto) {


        return successResponse("添加数据成功", "");
    }

}

