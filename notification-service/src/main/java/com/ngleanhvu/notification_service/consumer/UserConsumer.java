package com.ngleanhvu.notification_service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.common.constant.KafkaConst;
import com.ngleanhvu.common.dto.MailRecord;
import com.ngleanhvu.notification_service.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConsumer {
    private final ObjectMapper objectMapper;
    private final MailService mailService;

    @KafkaListener(topics = KafkaConst.USER_REGISTER_SUCCESS_TOPIC, groupId = KafkaConst.NOTIFICATION_GROUP_ID)
    public void sendEmailRegisteredConfirm(String message) throws JsonProcessingException {
        MailRecord mailRecord = objectMapper.readValue(message, MailRecord.class);
        mailService.send(mailRecord.to(), mailRecord.subject(), mailRecord.body());
    }
}
