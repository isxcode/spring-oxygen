package com.ispong.oxygen.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class QuartzDemoJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("测试定时器");
    }
}
