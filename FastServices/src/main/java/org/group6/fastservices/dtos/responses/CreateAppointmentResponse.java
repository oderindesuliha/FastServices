package org.group6.fastservices.dtos.responses;

import lombok.*;
import org.group6.fastservices.data.models.AppointmentStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateAppointmentResponse {
    private String id;
    private String offeringName;
    private String organizationName;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
    private int queuePosition;
    private LocalDateTime createdAt;
    private String message;
    private boolean success;
}

