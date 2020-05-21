package com.ispong.oxygen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目启动入口
 *
 * @author ispong
 * @version v0.1.0
 */
@RestController
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

        int i = 0;
        i = i + 26;
		SpringApplication.run(Application.class, args);
	}
}

