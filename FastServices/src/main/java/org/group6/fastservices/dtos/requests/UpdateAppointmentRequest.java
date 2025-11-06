package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import org.group6.fastservices.data.models.AppointmentStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class UpdateAppointmentRequest {

    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
}
