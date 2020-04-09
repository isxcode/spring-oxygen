package com.ispong.oxygen.scheduler;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 定时器请求对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class QuartzReq {

    /**
     * 任务名称
     */
    @NotBlank
    private String jobName;

    /**
     * 触发器名称
     */
    @NotBlank
    private String triggerName;

    /**
     * 组名称
     */
    @NotBlank
    private String groupName;

    /**
     * 任务class
     */
    @NotBlank
    private String jobClass;

    /**
     * 定时器类型
     */
    @NotBlank
    private String cronExpression;

}
