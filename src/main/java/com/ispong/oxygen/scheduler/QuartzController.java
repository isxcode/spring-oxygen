package com.ispong.oxygen.scheduler;

import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.flysql.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * qrtzTriggers controller
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("quartz")
public class QuartzController extends BaseController {

    public final QuartzService quartzService;

    public QuartzController(QuartzService quartzService) {

        this.quartzService = quartzService;
    }

    /**
     * 设置/修改定时器
     *
     * @param quartzReq 设置定时器请求对象
     * @since 0.0.1
     */
    @PostMapping("/settingQuartzJob")
    public ResponseEntity<BaseResponse<String>> settingQuartzJob(@Valid @RequestBody QuartzReq quartzReq) {

        quartzService.settingQuartzJob(quartzReq);

        return successResponse("定时器设置成功", "");
    }

}

