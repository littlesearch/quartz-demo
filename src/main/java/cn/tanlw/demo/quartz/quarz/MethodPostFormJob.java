package cn.tanlw.demo.quartz.quarz;

import cn.tanlw.demo.quartz.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author tanliwei
 * @create 2018/5/23
 */
@Slf4j
public class MethodPostFormJob implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //调用业务
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String url = "http://localhost:8080"+"/update";
        String result = HttpUtil.doPostForm(url,dataMap,"UTF-8");

//        log.info("Result:"+result);
        //回调结果给中心
    }
}
