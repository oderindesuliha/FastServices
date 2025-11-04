package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender mailSender;

    @Override
    public void sendRegistrationEmail(String to, String name) {
        if (mailSender == null) {
            logger.warn("MailSender not configured. Skipping email to: {}", to);
            return;
        }
        String subject = "Welcome to FastServices!";
        String body = String.format(
                "Hello %s, \n\nThank you for registering with FastServices! We're glad to have you on board. \n\nBest Regards, \nThe FastServices Team",
                name
        );

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            logger.info("✅ Registration email sent successfully to: {}", to);
        } catch (Exception e) {
            logger.error("❌ Error occurred while sending registration email to: {}", to, e);
        }
    }
    
    @Override
    public void sendAppointmentConfirmation(String to, String appointmentDetails) {
        String subject = "Appointment Confirmation - FastService";
        String body = "Your appointment has been confirmed!\n\n" + appointmentDetails;
    }
    
    @Override
    public void sendQueueNotification(String to, String queueDetails) {
        String subject = "Queue Status Update - FastService";
        String body = "Your queue status has been updated:\n\n" + queueDetails;
    }
}