package com.isxcode.isxcodespring.controller;

import com.isxcode.isxcodespring.annotation.Logs;
import com.isxcode.isxcodespring.annotation.TestAnno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-11
 */
@TestAnno(exclude = {"test2"})
@Logs(exclude = {"test2"})
@RestController
@RequestMapping("hello")
public class HelloController {


    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return new ResponseEntity<>("测试返回值1", HttpStatus.OK);
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test2() {

        return new ResponseEntity<>("测试返回值2", HttpStatus.OK);
    }

}
