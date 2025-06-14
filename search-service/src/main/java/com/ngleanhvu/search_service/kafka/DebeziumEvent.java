package com.ngleanhvu.search_service.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumEvent {
    // Getters and Setters
    @JsonProperty("op")
    private String operation; // c = create, u = update, d = delete

    @JsonProperty("before")
    private Map<String, Object> before;

    @JsonProperty("after")
    private Map<String, Object> after;

    @JsonProperty("source")
    private Source source;

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source {
        @JsonProperty("table")
        private String table;

        @JsonProperty("db")
        private String database;

    }
}
