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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PropertyEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PropertyEventConsumer.class);

    private final PropertySearchService propertySearchService;
    private static final String TABLE_NAME = "property";
    private static final String DATABASE_NAME = "booking_property_db";

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(
            topics = "booking_property_db.booking_property_db.property",
            groupId = "search-service-group"
    )
    public void consumePropertyEvent(@Payload String message,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                     @Header(KafkaHeaders.OFFSET) long offset) {

        logger.info("Received message from topic: {}, partition: {}, offset: {}", topic, partition, offset);

        try {
            DebeziumEvent event = objectMapper.readValue(message, DebeziumEvent.class);

            if (event.getSource() != null && TABLE_NAME.equals(event.getSource().getTable()) && DATABASE_NAME.equals(event.getSource().getDatabase())) {
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
                Map<String, Object> afterData = event.getAfter();
                if (afterData != null) {
                    PropertyDocument doc = mapToPropertyDocument(afterData);
                    propertySearchService.createPropertyDocument(doc); // lưu dữ liệu mới vào Elasticsearch
                    logger.info("Created new property with ID {}", doc.getId());
                }
                break;
            default:
                logger.warn("Unknown operation type: {}", operation);
        }
    }

    private PropertyDocument mapToPropertyDocument(Map<String, Object> data) {
        PropertyDocument doc = new PropertyDocument();

        doc.setId(getIntegerValue(data.get("id")));
        doc.setTitle(getStringValue(data.get("title")));
        doc.setDescription(getStringValue(data.get("description")));
        doc.setPropertyType(getIntegerValue(data.get("property_type_id")));
        doc.setRoomType(getIntegerValue(data.get("room_type_id")));
        doc.setAddressStreet(getStringValue(data.get("address_street")));
        doc.setCity(getIntegerValue(data.get("city_id")));
        doc.setCountry(getIntegerValue(data.get("country_id")));
        doc.setWard(getIntegerValue(data.get("ward_id")));
        doc.setDistrict(getIntegerValue(data.get("district_id")));
        doc.setPostalCode(getStringValue(data.get("postal_code")));
        doc.setThumbnail(getStringValue(data.get("thumbnail")));
        doc.setCurrencyCode(getStringValue(data.get("currency_code")));
        doc.setPricePerNight(getBigDecimalValue(data.get("price_per_night")));
        doc.setMaxGuests(getIntegerValue(data.get("max_guests")));
        doc.setNumBedrooms(getIntegerValue(data.get("num_bedrooms")));
        doc.setNumBeds(getIntegerValue(data.get("num_beds")));
        doc.setNumBathrooms(getBigDecimalValue(data.get("num_bathrooms")));
        doc.setActive(Boolean.TRUE.equals(data.get("active")) || Boolean.TRUE.equals(getBooleanValue(data.get("active"))));

        BigDecimal lat = getBigDecimalValue(data.get("latitude"));
        BigDecimal lon = getBigDecimalValue(data.get("longitude"));
        if (lat != null && lon != null) {
            doc.setLocation(new PropertyDocument.GeoPoint(lat, lon));
        }

        Object imagesObj = data.get("images");
        if (imagesObj instanceof List<?>) {
            doc.setImages(((List<?>) imagesObj).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList()));
        }

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

    private Boolean getBooleanValue(Object value) {
        if (value instanceof Boolean) return (Boolean) value;
        if (value instanceof String) return Boolean.parseBoolean((String) value);
        if (value instanceof Number) return ((Number) value).intValue() != 0;
        return false;
    }

    private Double getDoubleValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number) return ((Number) value).doubleValue();
        return Double.parseDouble(value.toString());
    }
}

