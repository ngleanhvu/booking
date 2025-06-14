package com.ngleanhvu.search_service.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumEvent {
    @JsonProperty("op")
    private String operation; // c = create, u = update, d = delete

    @JsonProperty("before")
    private Map<String, Object> before;

    @JsonProperty("after")
    private Map<String, Object> after;

    @JsonProperty("source")
    private Source source;

    // Getters and Setters
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public Map<String, Object> getBefore() { return before; }
    public void setBefore(Map<String, Object> before) { this.before = before; }

    public Map<String, Object> getAfter() { return after; }
    public void setAfter(Map<String, Object> after) { this.after = after; }

    public Source getSource() { return source; }
    public void setSource(Source source) { this.source = source; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source {
        @JsonProperty("table")
        private String table;

        @JsonProperty("db")
        private String database;

        public String getTable() { return table; }
        public void setTable(String table) { this.table = table; }

        public String getDatabase() { return database; }
        public void setDatabase(String database) { this.database = database; }
    }
}
