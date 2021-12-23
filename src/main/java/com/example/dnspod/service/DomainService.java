package com.example.dnspod.service;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.dnspod.config.DomainConfig;
import com.example.dnspod.entry.Record;
import com.example.dnspod.entry.RecordCreate;
import com.example.dnspod.entry.RecordUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caitao
 * @date 2021/12/20
 */
@Service("domainService")
@Slf4j
public class DomainService {

    @Resource
    private DomainConfig domainConfig;

    final String point = ".";


    public String getIp() {
        String res = HttpUtil.get(domainConfig.getIpaddress());
        return ReUtil.get(domainConfig.getIpReg(), res, 0);
    }

    public String updateRecord() {
        String curentIp = getIp();
        Map<String, Object> map = new HashMap<>(8);
        map.put("login_token",domainConfig.getToken());
        map.put("format", domainConfig.getFomat());
        map.put("domain", domainConfig.getDomain());
        map.put("sub_domain", domainConfig.getSubDomain());
        map.put("record_type", "A");
        map.put("record_line","默认");
        map.put("value", curentIp);
        map.put("mx", "10");
        Record.RecordsDTO records = getRecord();

        String ddns = domainConfig.getSubDomain() + point + domainConfig.getDomain();
        String mappint = ddns + ":" + curentIp;
        if(records == null){
            String post = HttpUtil.post(domainConfig.getRecoredCreate(), map);
            RecordCreate recordCreate = JSONUtil.toBean(post, RecordCreate.class);
            if(recordCreate.getStatus().getCode().equals("1")){
                return "新增成功:" + mappint;
            }
            return "新增失败:" + recordCreate;

        }
        if(records.getValue().equals(curentIp)){
            return "无需更新:" +mappint;
        }
        log.info("当前IP{}不等于记录IP{}", curentIp, records.getValue());
        map.put("record_id", records.getId());
        String post = HttpUtil.post(domainConfig.getRecoredModify(), map);
        RecordUpdate recordUpdate = JSONUtil.toBean(post, RecordUpdate.class);
        if(recordUpdate.getStatus().getCode().equals("1")){
            return "更新成功" +mappint;
        }
        return recordUpdate.toString();

    }

    public Record.RecordsDTO getRecord() {
        final String listUrl = domainConfig.getRecoredList();
        final String sub =  domainConfig.getSubDomain();
        Map<String, Object> map = new HashMap<>(8);
        map.put("login_token",domainConfig.getToken());
        map.put("domain", domainConfig.getDomain());
        map.put("sub_domain", domainConfig.getSubDomain());
        String post = HttpUtil.post(listUrl, map);
        Record record = JSONUtil.toBean(post, Record.class);
        if(record.getStatus().getCode().equals("1")){
            List<Record.RecordsDTO> records = record.getRecords();
            for (Record.RecordsDTO recordsDTO : records) {
                if(sub.equals(recordsDTO.getName())){
                    return recordsDTO;
                }
            }
        }
        return null;
    }
}
