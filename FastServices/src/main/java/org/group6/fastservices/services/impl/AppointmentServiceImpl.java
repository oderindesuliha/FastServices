package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.data.models.Customer;
import org.group6.fastservices.data.repositories.AppointmentRepository;
import org.group6.fastservices.data.repositories.UserRepository;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;
import org.group6.fastservices.exceptions.AccessDeniedException;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.security.AuthenticatedPrincipal;
import org.group6.fastservices.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final UserRepository userRepository;
    private AppointmentRepository appointmentRepository;
    
    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        Customer customer = getAuthenticatedCustomer();

        return null;
    }



    @Override
    public Appointment getAppointmentById(String id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }
    
    @Override
    public List<Appointment> getAppointmentsByCustomerId(String customerId) {
        return appointmentRepository.findByUserId(customerId);
    }
    
    @Override
    public List<Appointment> getAppointmentsByOfferingId(String offeringId) {
        return appointmentRepository.findByOfferingId(offeringId);
    }
    
    @Override
    public List<Appointment> getAppointmentsByQueueId(String queueId) {
        return appointmentRepository.findByQueueId(queueId);
    }
    
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    @Override
    public Appointment updateAppointment(String id, Appointment appointment) {
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
        if(auth == null || !auth.isAuthenticated()) throw new RuntimeException("Unauthenticated access");

        var customer = (AuthenticatedPrincipal) auth.getPrincipal();
        if(customer.isOrganizationAccount()) throw new AccessDeniedException("Access not granted");
        return userRepository.findByEmail(customer.getUsername())
                .orElseThrow(()-> new)

    }

}