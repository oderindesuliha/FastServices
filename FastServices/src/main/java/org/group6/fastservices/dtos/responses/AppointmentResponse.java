package org.group6.fastservices.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AppointmentResponse {

    private String id;
    private String OfferingName;
    private String organizationName;
    private String customerName;
    private LocalDateTime appointmentDate;
    private String status;
    private String queuePosition;
    private LocalDateTime createdAt;
}
