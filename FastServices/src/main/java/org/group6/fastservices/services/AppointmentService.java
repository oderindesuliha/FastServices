package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.responses.AppointmentResponse;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;

import java.util.List;

public interface AppointmentService {
    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
    AppointmentResponse getAppointmentById(String id);
    List<Appointment> getAppointmentsByCustomerId(String customerId);
    List<Appointment> getAppointmentsByOfferingId(String offeringId);
    List<Appointment> getAppointmentsByQueueId(String queueId);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(String id, Appointment appointment);
    void deleteAppointment(String id);

}


// ------------------ READ ------------------
//@Override
//public AppointmentResponse getAppointmentById(String id) {
//    Appointment appointment = appointmentRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
//    return mapToDto(appointment);
//}

//@Override
//public List<AppointmentResponse> getAppointmentsByCustomerId(String customerId) {
//    return appointmentRepository.findByUserId(customerId)
//            .stream().map(this::mapToDto).collect(Collectors.toList());
//}
//
//@Override
//public List<AppointmentResponse> getAppointmentsByOfferingId(String offeringId) {
//    return appointmentRepository.findByOfferingId(offeringId)
//            .stream().map(this::mapToDto).collect(Collectors.toList());
//}
//
//@Override
//public List<AppointmentResponse> getAppointmentsByQueueId(String queueId) {
//    return appointmentRepository.findByQueueId(queueId)
//            .stream().map(this::mapToDto).collect(Collectors.toList());
//}

//@Override
//public List<AppointmentResponse> getAllAppointments() {
//    return appointmentRepository.findAll()
//            .stream().map(this::mapToDto).collect(Collectors.toList());
//}
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

