package cn.tanlw.demo.quartz.quarz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * http://www.quartz-scheduler.org/
 * Quartz框架入门学习(一) https://blog.csdn.net/u014705021/article/details/53148426
 * 任务调度框架Quartz（四） https://blog.csdn.net/zixiao217/article/details/53075009
 *
 * @create 2018/5/17
 */
@Component
@Slf4j
public class MySchedule {

    @Autowired
    private Environment environment;

    public void process() {
        foreverJob();
        cronJob();
        calendarJob();
        //TODO 线程池 锁 功能管理 日志
    }

    /**
     * 一直重复的作业
     */
    private void foreverJob() {
        try {
            System.out.println("MySchedule" + "-" + "process");
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our MethodGetJob class
            JobDetail detail = newJob(MethodGetJob.class)
                    .withIdentity("foreverJob", "group1")
                    .usingJobData("id", environment.getProperty("job.param.id"))
                    .build();

            // Trigger the job to process now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInMilliseconds(Long.parseLong(environment.getProperty("job.trigger.interval")))
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(detail, trigger);


            // and start it off
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("foreverJob",e);
        }
    }

    /**
     * 指定日期执行的作业
     */
    private void calendarJob() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail detail = newJob(MethodPostJsonJob.class)
                    .withIdentity("cronJobTime", "group1")
                    .usingJobData("id",environment.getProperty("job.param.id")).build();
            CronTrigger cronTrigger = newTrigger().withIdentity("cronTriggerTime","group2")
                    .withSchedule(cronSchedule(environment.getProperty("job.cron.formula.time"))).build();

            scheduler.scheduleJob(detail, cronTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("calendarJob",e);
        }
    }


    /**
     * 支持Cron表达式的作业
     */
    private void cronJob() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail detail = newJob(MethodPostFormJob.class)
                    .withIdentity("cronJob", "group1")
                    .usingJobData("id",environment.getProperty("job.param.id")).build();
            CronTrigger cronTrigger = newTrigger().withIdentity("cronTrigger","group2")
                    .withSchedule(cronSchedule(environment.getProperty("job.cron.formula"))).build();

            scheduler.scheduleJob(detail, cronTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("cronJob",e);
        }
    }
}
