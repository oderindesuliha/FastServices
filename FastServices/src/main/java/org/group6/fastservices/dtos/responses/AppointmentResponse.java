package org.group6.fastservices.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class AppointmentResponse {

    private String id;
    private String offeringName;
    private String organizationName;
    private String customerName;
    private LocalDateTime appointmentDate;
    private String status;
    private int queuePosition;
    private LocalDateTime createdAt;
}
