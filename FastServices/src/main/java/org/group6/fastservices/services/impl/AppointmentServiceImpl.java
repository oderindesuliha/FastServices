package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.*;
import org.group6.fastservices.data.repositories.*;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;
import org.group6.fastservices.dtos.responses.AppointmentResponse;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;
import org.group6.fastservices.exceptions.*;
import org.group6.fastservices.security.AuthenticatedPrincipal;
import org.group6.fastservices.services.AppointmentService;
import org.group6.fastservices.services.QueueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final CustomerRepository customerRepository;
    private final AppointmentRepository appointmentRepository;
    private final OfferingRepository offeringRepository;
    private final QueueService queueService;

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        Customer customer = getAuthenticatedCustomer();
        Offering offering = offeringRepository.findById(request.getOfferingId())
                .orElseThrow(() -> new OfferingNotFoundException("Service not found"));

        CreateQueueRequest queueRequest = new CreateQueueRequest(
                offering.getId(),
                offering.getName(),
                offering.getOrganization().getId(),
                offering.getOrganization().getCode()
        );

        CreateQueueResponse queueResponse = queueService.findOrCreateQueueForOffering(queueRequest);
        Queue queue = queueService.getQueueById(queueResponse.getId());
        int nextPosition = appointmentRepository.countByQueueId(queue.getId()) + 1;

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setUser(customer);
        appointment.setOffering(offering);
        appointment.setQueue(queue);
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setQueuePosition(nextPosition);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return CreateAppointmentResponse.builder()
                .id(savedAppointment.getId())
                .offeringName(offering.getName())
                .organizationName(offering.getOrganization().getName())
                .appointmentDate(savedAppointment.getAppointmentDate())
                .status(savedAppointment.getStatus())
                .queuePosition(savedAppointment.getQueuePosition())
                .createdAt(savedAppointment.getCreatedAt())
                .message("Appointment created successfully")
                .success(true)
                .build();
    }

    @Override
    public AppointmentResponse getAppointmentById(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
        return mapToResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByCustomerId(String customerId) {
        return appointmentRepository.findByUserId(customerId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByOfferingId(String offeringId) {
        return appointmentRepository.findByOfferingId(offeringId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByQueueId(String queueId) {
        return appointmentRepository.findByQueueId(queueId);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public AppointmentResponse updateAppointment(String id, AppointmentResponse appointment) {
        if (appointmentRepository.existsById(id)) {
            appointment.setId(id);
            return appointmentRepository.save(appointment);
        }
        throw new ResourceNotFoundException("Appointment not found with id: " + id);
    }

    @Override
    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }

    private Customer getAuthenticatedCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated())
            throw new RuntimeException("Unauthenticated access");

        var customer = (AuthenticatedPrincipal) auth.getPrincipal();
        if(customer.isOrganizationAccount())
            throw new AccessDeniedException("Access not granted");

        return customerRepository.findCustomerByEmail(customer.getUsername())
                .orElseThrow(()-> new AccountNotFoundException("Authenticated customer not found"));
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .offeringName(appointment.getOffering().getName())
                .organizationName(appointment.getOffering().getOrganization().getName())
                .customerName(appointment.getUser().getFirstName())
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus().name())
                .queuePosition(appointment.getQueuePosition())
                .createdAt(appointment.getCreatedAt())
                .build();
    }
}