package com.ngleanhvu.notification_service.service;

public interface MailService {
    void send(String to, String subject, String body);
}
