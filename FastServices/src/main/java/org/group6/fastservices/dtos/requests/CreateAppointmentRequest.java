package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import org.group6.fastservices.data.models.AppointmentStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateAppointmentRequest {
    private String userId;
    private String offeringId;
    private String queueId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
}