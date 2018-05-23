package cn.tanlw.demo.quartz.quarz;

import cn.tanlw.demo.quartz.http.HttpUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author tanliwei
 * @create 2018/5/23
 */
public class MethodPostJsonJob implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //调用业务
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String url = "http://localhost:8080" + "/update2";
        String result = HttpUtil.doPostJson(url, dataMap, "UTF-8");
        //回调结果给中心
    }
}
