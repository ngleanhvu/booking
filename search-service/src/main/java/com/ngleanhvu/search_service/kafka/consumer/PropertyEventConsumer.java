package com.ngleanhvu.search_service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.search_service.document.PropertyDocument;
import com.ngleanhvu.search_service.kafka.DebeziumEvent;
import com.ngleanhvu.search_service.service.PropertySearchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PropertyEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PropertyEventConsumer.class);

    private final PropertySearchService propertySearchService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "property_db.property_db.properties", groupId = "search-service-group")
    public void consumePropertyEvent(@Payload String message,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                     @Header(KafkaHeaders.OFFSET) long offset) {

        logger.info("Received message from topic: {}, partition: {}, offset: {}", topic, partition, offset);

        try {
            DebeziumEvent event = objectMapper.readValue(message, DebeziumEvent.class);

            if (event.getSource() != null && "properties".equals(event.getSource().getTable())) {
                processPropertyEvent(event);
            }

        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }

    private void processPropertyEvent(DebeziumEvent event) {
        String operation = event.getOperation();

        switch (operation) {
            case "c":

            default:
                logger.warn("Unknown operation type: {}", operation);
        }
    }

    private PropertyDocument mapToPropertyDocument(Map<String, Object> data) {
        PropertyDocument doc = new PropertyDocument();



        return doc;
    }

    private String getStringValue(Object value) {
        return value != null ? value.toString() : null;
    }

    private Long getLongValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number) return ((Number) value).longValue();
        return Long.parseLong(value.toString());
    }

    private Integer getIntegerValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number) return ((Number) value).intValue();
        return Integer.parseInt(value.toString());
    }

    private BigDecimal getBigDecimalValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number) return BigDecimal.valueOf(((Number) value).doubleValue());
        return new BigDecimal(value.toString());
    }

    private LocalDateTime getLocalDateTimeValue(Object value) {
        if (value == null) return null;
        if (value instanceof Long) {
            return LocalDateTime.ofEpochSecond(((Long) value) / 1000000, 0, java.time.ZoneOffset.UTC);
        }
        return LocalDateTime.parse(value.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

