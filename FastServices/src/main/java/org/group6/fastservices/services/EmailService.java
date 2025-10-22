package org.group6.fastservices.services;

public interface EmailService {
    void sendRegistrationEmail(String to, String name);
    void sendAppointmentConfirmation(String to, String appointmentDetails);
    void sendQueueNotification(String to, String queueDetails);
}