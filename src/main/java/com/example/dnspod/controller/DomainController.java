package com.example.dnspod.controller;

import com.example.dnspod.entry.Record;
import com.example.dnspod.service.DomainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caitao
 * @date 2021/12/21
 */
@RestController
public class DomainController {

    @Resource
    private DomainService domainService;

    @GetMapping("getIp")
    public String getIp(){
        return domainService.getIp();
    }

    @GetMapping("updateRecord")
    public String updateRecord(){
        return domainService.updateRecord();
    }


    @GetMapping("getRecord")
    public Record.RecordsDTO getRecord(){
        return domainService.getRecord();
    }

}
