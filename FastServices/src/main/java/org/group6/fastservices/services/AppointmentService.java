package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.UpdateAppointmentRequest;
import org.group6.fastservices.dtos.responses.AppointmentResponse;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;
import org.group6.fastservices.dtos.responses.GenericResponse;

import java.util.List;

public interface AppointmentService {
    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
    AppointmentResponse getAppointmentById(String id);
    List<AppointmentResponse> getAppointmentsByCustomerId(String customerId);
    List<AppointmentResponse> getAppointmentsByOfferingId(String offeringId);
    List<AppointmentResponse> getAppointmentsByQueueId(String queueId);
    List<AppointmentResponse> getAllAppointments();
    AppointmentResponse updateAppointment(String id, UpdateAppointmentRequest request);
    GenericResponse deleteAppointment(String id);
}
