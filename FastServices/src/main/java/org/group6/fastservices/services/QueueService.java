package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;

public interface QueueService {
    CreateQueueResponse findOrCreateQueueForOffering(CreateQueueRequest request);
//    Queue getQueueById(String id);
//    List<Queue> getQueuesByOrganizationId(String organizationId);
//    List<Queue> getQueuesByOfferingId(String offeringId);
//    List<Queue> getAllQueues();
//    Queue updateQueue(String id, Queue queue);
//    void deleteQueue(String id);
}


//        Queue queue = modelMapper.map(queueResponse, Queue.class);
//
//        // 4️⃣ Determine next queue position
//        int nextPosition = queue.getAppointments() == null ? 1 : queue.getAppointments().size() + 1;

        // 5️⃣ Create and save appointment
//        Appointment appointment = new Appointment();
//        appointment.setUser(customer);
//        appointment.setOffering(offering);
//        appointment.setQueue(queue);
//        appointment.setAppointmentDate(LocalDateTime.now());
//        appointment.setStatus(AppointmentStatus.PENDING);
//        appointment.setQueuePosition(nextPosition);
//
//        Appointment saved = appointmentRepository.save(appointment);
//

//
//    CreateQueueResponse queueResponse = queueService.findOrCreateQueueForOffering(queueRequest);
//
//    // 4️⃣ Create and populate the appointment
//    Appointment appointment = new Appointment();
//    appointment.setAppointmentDate(request.getAppointmentDate());
//    appointment.setStatus(AppointmentStatus.PENDING);
//    appointment.setCreatedAt(LocalDateTime.now());
//    appointment.setUser(customer);
//    appointment.setOffering(offering);
//
//    // Link the queue (only by ID, no need to remap the full object)
//    Queue queue = new Queue();
//    queue.setId(queueResponse.getId());
//    appointment.setQueue(queue);
//
//    // 5️⃣ Save appointment
//    Appointment savedAppointment = appointmentRepository.save(appointment);
//
//    // 6️⃣ Build and return response
//    return CreateAppointmentResponse.builder()
//            .id(savedAppointment.getId())
//            .offeringName(offering.getName())
//            .organizationName(offering.getOrganization().getName())
//            .appointmentDate(savedAppointment.getAppointmentDate())
//            .status(savedAppointment.getStatus())
//            .createdAt(savedAppointment.getCreatedAt())
//            .message("Appointment created successfully.")
//            .newlyCreated(true)
//            .build();
//}

