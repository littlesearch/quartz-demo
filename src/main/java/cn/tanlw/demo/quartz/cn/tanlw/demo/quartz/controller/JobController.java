package cn.tanlw.demo.quartz.cn.tanlw.demo.quartz.controller;

import cn.tanlw.demo.quartz.entity.JobReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author tanliwei
 * @create 2018/5/23
 */
@RestController
@Slf4j
public class JobController {

    @RequestMapping(value = "/job")
    public void test(@ModelAttribute JobReq req){
        log.info("job-"+System.currentTimeMillis()+"-"+Thread.currentThread().getName()+"- param:"+req.getId());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(JobReq req){
        log.info("UrlEncoded request-"+System.currentTimeMillis()+"-"+Thread.currentThread().getName()+"- param:"+req.getId());
        return "success";
    }

    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public String update2(@RequestBody JobReq req){
        log.info("application/json request"+System.currentTimeMillis()+"-"+Thread.currentThread().getName()+"- param:"+req.getId());
        return "success";
    }
}
