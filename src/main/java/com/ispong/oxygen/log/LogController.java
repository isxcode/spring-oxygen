package com.ispong.oxygen.log;

import com.ispong.oxygen.flysql.common.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * log controller
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("log")
public class LogController extends BaseController {

    public final LogService logService;

    public LogController(LogService logService) {

        this.logService = logService;
    }

    /**
     * query LogEntity
	 *
     * @return String
     */
    @GetMapping("queryLog")
    public List<LogEntity> queryLog() {

        return logService.queryLog();
    }

}

