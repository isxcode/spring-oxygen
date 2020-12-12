package com.isxcode.oxygen.freecode.controller;

import com.isxcode.oxygen.freecode.service.FreecodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * freecode controller
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
     * generate code api
     *
     * @param tableNames tableNames(split ,)
     * @return ResponseEntity
     * @since 0.0.1
     */
    @GetMapping("/generate")
    public ResponseEntity<String> generateCode(@RequestParam String tableNames) {

        freecodeService.startFreecode(tableNames);

        return new ResponseEntity<>("welcome to use oxygen-freecode! generate code success", HttpStatus.OK);
    }

}
