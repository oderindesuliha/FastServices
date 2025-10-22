package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendRegistrationEmail(String to, String subject, String body) {
        if (mailSender == null) {
            logger.warn("Email service not configured. Skipping email to: {}", to);
            return;
        }
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            logger.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            logger.error("Error occurred while sending email to: {}", to, e);
        }
    }
    
    @Override
    public void sendAppointmentConfirmation(String to, String appointmentDetails) {
        String subject = "Appointment Confirmation - FastService";
        String body = "Your appointment has been confirmed!\n\n" + appointmentDetails;
        sendEmail(to, subject, body);
    }
    
    @Override
    public void sendQueueNotification(String to, String queueDetails) {
        String subject = "Queue Status Update - FastService";
        String body = "Your queue status has been updated:\n\n" + queueDetails;
        sendEmail(to, subject, body);
    }
}