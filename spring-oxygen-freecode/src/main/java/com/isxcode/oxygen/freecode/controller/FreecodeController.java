package com.isxcode.oxygen.freecode.controller;

import com.isxcode.oxygen.flysql.response.SuccessResponse;
import com.isxcode.oxygen.freecode.service.FreecodeService;
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
     * @since 0.0.1
     */
    @SuccessResponse("generate success!!!")
    @GetMapping("/generate")
    public void generateCode(@RequestParam String tableNames) {

        freecodeService.startFreecode(tableNames);
    }

}
