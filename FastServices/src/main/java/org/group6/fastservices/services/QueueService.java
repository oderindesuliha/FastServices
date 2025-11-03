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

