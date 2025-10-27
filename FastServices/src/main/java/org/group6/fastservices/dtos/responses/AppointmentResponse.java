package org.group6.fastservices.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.group6.fastservices.data.models.AppointmentStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class AppointmentResponse {
    private String offeringId;
    private String queueId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
    private int queuePosition;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}