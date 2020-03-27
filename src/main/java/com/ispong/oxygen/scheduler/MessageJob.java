package com.ispong.oxygen.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class MessageJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.err.println("Hello!  HelloJob is executing.");
    }
}
