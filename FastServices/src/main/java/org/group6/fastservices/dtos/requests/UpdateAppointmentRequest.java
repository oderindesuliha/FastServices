package org.group6.fastservices.dtos.requests;

import org.group6.fastservices.data.models.AppointmentStatus;

import java.time.LocalDateTime;

public class UpdateAppointmentRequest {

    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
}
