package io.justina.h106javareact.adapters.timer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
/*
public class DailyReminderJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        // TODO. JOB TO EXECUTE.
    }

    public void initializeTimer() {
        try {
            JobDetail appointmentReminder = JobBuilder.newJob(DailyReminderJob.class).build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(8, 0))
                    .forJob(appointmentReminder)
                    .build();

            org.quartz.Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(appointmentReminder, trigger);
        } catch (Exception e) { e.printStackTrace(); }
    }

}*/