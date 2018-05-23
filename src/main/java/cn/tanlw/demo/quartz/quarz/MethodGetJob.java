package cn.tanlw.demo.quartz.quarz;

import cn.tanlw.demo.quartz.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author tanliwei
 * @create 2018/5/17
 */
@Slf4j
public class MethodGetJob implements org.quartz.Job {

    public MethodGetJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //调用业务
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        StringBuilder sb = new StringBuilder();
        dataMap.keySet().stream().forEach(item-> sb.append(item+"="+ dataMap.get(item)));
        String url = "http://localhost:8080"+"/job?"+sb;
        HttpUtil.doGet(url);
        //回调结果给中心
    }
}
