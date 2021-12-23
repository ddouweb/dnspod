package com.example.dnspod.scheduled;

import com.example.dnspod.service.DomainService;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author caitao
 * @date 2021/12/21
 */

@Component
@EnableScheduling
public class RunPod {

    @Resource
    private DomainService domainService;
    @Scheduled(cron="0 */5 * * * *")
    public void doSomething() {
        System.out.println("检查更新！");
        String result = domainService.updateRecord();
        System.out.println(result);
    }
}
