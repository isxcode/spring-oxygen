package com.ispong.oxygen.scheduler;

import com.ispong.oxygen.exception.OxygenException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * qrtzTriggers service
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@Service
public class QuartzService {

    private final Scheduler scheduler;

    private final QuartzRepository quartzRepository;

    public QuartzService(QuartzRepository quartzRepository, SchedulerFactoryBean schedulerFactoryBean) {

        this.scheduler = schedulerFactoryBean.getScheduler();
        this.quartzRepository = quartzRepository;
    }

    /**
     * 设置定时器任务
     *
     * @param quartzReq 定时器请求对象
     * @since 0.0.1
     */
    @SuppressWarnings("unchecked")
    public void settingQuartzJob(QuartzReq quartzReq) {

        try {
            scheduler.start();
        } catch (SchedulerException e) {
            throw new OxygenException("scheduler.start() exception");
        }

        Class<? extends Job> jobClass;
        try {
            jobClass = (Class<? extends Job>) Class.forName(quartzReq.getJobClass());
        } catch (ClassNotFoundException e) {
            throw new OxygenException("job class notFoundException");
        }

        JobDetail job = newJob(jobClass)
            .withIdentity(quartzReq.getJobName(), quartzReq.getGroupName())
            .build();

        Trigger trigger = newTrigger()
            .withIdentity(quartzReq.getTriggerName(), quartzReq.getGroupName())
            .startNow()
            .withSchedule(
                cronSchedule(quartzReq.getCronExpression())
                    .inTimeZone(TimeZone.getTimeZone("GMT+8")))
            .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new OxygenException("scheduler.scheduleJob(job, trigger) exception");
        }
    }

}