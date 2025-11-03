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



//    @Override
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
//        // 1️⃣ Get authenticated customer
//        Customer customer = getAuthenticatedCustomer();
//
//        // 2️⃣ Find offering
//        Offering offering = offeringRepository.findOfferingByName(request.getOfferingName())
//                .orElseThrow(() -> new OfferingNotFoundException("Service not found: " + request.getOfferingName()));
//
//        // 3️⃣ Get or create queue for this offering
//        var queueRequest = new org.group6.fastservices.dtos.requests.CreateQueueRequest(
//                offering.getId(),
//                offering.getName(),
//                offering.getOrganization().getId(),
//                offering.getOrganization().getName()
//        );
//        var queueResponse = queueService.findOrCreateQueueForOffering(queueRequest);

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
//        // 6️⃣ Build response
//        return new CreateAppointmentResponse(
//                saved.getAppointmentDate(),
//                saved.getStatus(),
//                saved.getQueuePosition(),
//                saved.getCreatedAt(),
//                "Appointment created successfully.",
//                true
//        );
//    }
//
//    private Customer getAuthenticatedCustomer() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null || !auth.isAuthenticated()) throw new RuntimeException("Unauthenticated access");
//
//        var principal = (AuthenticatedPrincipal) auth.getPrincipal();
//        if (principal.isOrganizationAccount()) throw new AccessDeniedException("Access denied for organization account");
//
//        return customerRepository.findCustomerByEmail(principal.getUsername())
//                .orElseThrow(() -> new AccountNotFoundException("Authenticated customer not found"));
//    }
//
//    // other CRUD methods unchanged
//}

