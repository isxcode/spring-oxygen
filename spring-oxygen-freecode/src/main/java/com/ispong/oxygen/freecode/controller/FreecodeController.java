/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.freecode.controller;

import com.ispong.oxygen.freecode.service.FreecodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 代码自动生成接口
 *
 * @author ispong
 * @since 0.0.1
 */
@RequestMapping("/freecode")
public class FreecodeController {

    private final FreecodeService freecodeService;

    public FreecodeController(FreecodeService freecodeService) {

        this.freecodeService = freecodeService;
    }

    /**
     * 代码生成接口
     *
     * @param tableName 表名包括逗号
     * @return ResponseEntity
     * @since 0.0.1
     */
    @GetMapping("/generate")
    public ResponseEntity<String> generateCode(@RequestParam String tableName) {

        freecodeService.startFreecode(tableName);

        return new ResponseEntity<>("welcome to use oxygen-freecode! generate code success", HttpStatus.OK);
    }

}
