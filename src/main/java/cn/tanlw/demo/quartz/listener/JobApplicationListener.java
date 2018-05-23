package cn.tanlw.demo.quartz.listener;

import cn.tanlw.demo.quartz.quarz.MySchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author tanliwei
 * @create 2018/5/22
 */
@Slf4j
@Component("myJobApplicationListener")
public class JobApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private Environment environment;
    @Autowired
    private MySchedule mySchedule;
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {

            try {
                if (environment == null) {
                    System.out.println("environment is null");
                }
                System.out.println("JobApplicationListener - onApplicationEvent");
                mySchedule.process();
            } catch (Exception e) {
                log.error("执行业务调度失败", e);
            }
        }
    }
}
