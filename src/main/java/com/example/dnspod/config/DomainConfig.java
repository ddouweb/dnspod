package com.example.dnspod.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author caitao
 * @date 2021/12/20
 */
@NoArgsConstructor
@Data
@Configuration
@Getter
@Setter
public class DomainConfig {

    @Value("${ipaddress.real:http://checkip.dyndns.com}")
    private String ipaddress;

    @Value("${ipaddress.reg:\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}}")
    private String ipReg;

    @Value("${recored.list}")
    private String recoredList;

    @Value("${recored.create}")
    private String recoredCreate;

    @Value("${recored.modify}")
    private String recoredModify;

    @Value("${recored.delete}")
    private String recoredDelete;

    @Value("${token}")
    private String token;

    @Value("${format}")
    private String fomat;

    @Value("${domain.main}")
    private String domain;

    @Value("${domain.sub}")
    private String subDomain;
    @JsonProperty("status")
    private StatusDTO status;
    @JsonProperty("domain")
    private DomainDTO domainX;
    @JsonProperty("info")
    private InfoDTO info;
    @JsonProperty("records")
    private List<RecordsDTO> records;

    @NoArgsConstructor
    @Data
    public static class StatusDTO {
        @JsonProperty("code")
        private String code;
        @JsonProperty("message")
        private String message;
        @JsonProperty("created_at")
        private String createdAt;
    }

    @NoArgsConstructor
    @Data
    public static class DomainDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("punycode")
        private String punycode;
        @JsonProperty("grade")
        private String grade;
        @JsonProperty("owner")
        private String owner;
        @JsonProperty("ext_status")
        private String extStatus;
        @JsonProperty("ttl")
        private Integer ttl;
        @JsonProperty("min_ttl")
        private Integer minTtl;
        @JsonProperty("dnspod_ns")
        private List<String> dnspodNs;
        @JsonProperty("status")
        private String status;
        @JsonProperty("can_handle_at_ns")
        private Boolean canHandleAtNs;
    }

    @NoArgsConstructor
    @Data
    public static class InfoDTO {
        @JsonProperty("sub_domains")
        private String subDomains;
        @JsonProperty("record_total")
        private String recordTotal;
        @JsonProperty("records_num")
        private String recordsNum;
    }

    @NoArgsConstructor
    @Data
    public static class RecordsDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("ttl")
        private String ttl;
        @JsonProperty("value")
        private String value;
        @JsonProperty("enabled")
        private String enabled;
        @JsonProperty("status")
        private String status;
        @JsonProperty("updated_on")
        private String updatedOn;
        @JsonProperty("name")
        private String name;
        @JsonProperty("line")
        private String line;
        @JsonProperty("line_id")
        private String lineId;
        @JsonProperty("type")
        private String type;
        @JsonProperty("weight")
        private Object weight;
        @JsonProperty("monitor_status")
        private String monitorStatus;
        @JsonProperty("remark")
        private String remark;
        @JsonProperty("use_aqb")
        private String useAqb;
        @JsonProperty("mx")
        private String mx;
    }
}
