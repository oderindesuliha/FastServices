package org.group6.fastservices.services;

import org.group6.fastservices.dtos.responses.AppointmentResponse;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;

import java.util.List;

public interface AppointmentService {
    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
    AppointmentResponse getAppointmentById(String id);
    List<AppointmentResponse> getAppointmentsByCustomerId(String customerId);
    List<AppointmentResponse> getAppointmentsByOfferingId(String offeringId);
    List<AppointmentResponse> getAppointmentsByQueueId(String queueId);
    List<AppointmentResponse> getAllAppointments();
    AppointmentResponse updateAppointment(String id, AppointmentResponse appointment);
    void deleteAppointment(String id);

}



//
//// ------------------ UPDATE ------------------
//@Override
//public AppointmentResponse updateAppointment(String id, UpdateAppointmentRequest request) {
//    Appointment appointment = appointmentRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
//
//    appointment.setAppointmentDate(request.getAppointmentDate());
//    appointment.setStatus(request.getStatus());
//    appointment.setUpdatedAt(LocalDateTime.now());
//
//    Appointment updated = appointmentRepository.save(appointment);
//    return mapToDto(updated);
//}

// ------------------ DELETE ------------------
//@Override
//public GenericResponse deleteAppointment(String id) {
//    Appointment appointment = appointmentRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
//
//    appointmentRepository.delete(appointment);
//
//    return new GenericResponse(true, "Appointment deleted successfully");
//}

