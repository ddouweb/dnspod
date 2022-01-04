package com.example.dnspod.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author caitao
 * @date 2021/12/21
 */
@NoArgsConstructor
@Data
public class RecordUpdate {
    @JsonProperty("status")
    private StatusDTO status;
    @JsonProperty("record")
    private RecordDTO record;

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
    public static class RecordDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("value")
        private String value;
        @JsonProperty("status")
        private String status;
        @JsonProperty("weight")
        private Object weight;
    }
}
