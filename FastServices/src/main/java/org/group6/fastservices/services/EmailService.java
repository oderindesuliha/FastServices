package org.group6.fastservices.services;

public interface EmailService {
    void sendRegistrationEmail(String to, String name);
    void sendAppointmentConfirmation(String to, String appointmentDetails);
    void sendQueueNotification(String to, String queueDetails);
}



package org.group6.fastservices.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentResponse {
    private String id;
    private String offeringName;
    private String organizationName;
    private String customerName;
    private LocalDateTime appointmentDate;
    private String status;
    private Integer queuePosition;
    private LocalDateTime createdAt;
}
