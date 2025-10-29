package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.data.models.AppointmentStatus;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.AppointmentRepository;
import org.group6.fastservices.data.repositories.CustomerRepository;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.requests.RegisterUserRequest;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;
import org.group6.fastservices.dtos.responses.RegisterUserResponse;
import org.group6.fastservices.security.AuthenticatedPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceImplTest {

    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired

    @BeforeEach
    void setUp() {
        appointmentRepository.deleteAll();
        customerRepository.deleteAll();

    }

    @AfterEach
    void tearDown() {
        appointmentRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void testCanCreateAppointment() {
        CreateAppointmentResponse response  =  createAppointment();
        assertTrue(response.isSuccess());
    }

    private CreateAppointmentResponse createAppointment() {
        CreateAppointmentRequest request = new CreateAppointmentRequest();
        request.setAppointmentDate(LocalDateTime.of(2025, 11, 16, 5, 30));
        request.setStatus(AppointmentStatus.PENDING);

        return appointmentService.createAppointment(request);
    }

    private void mockCustomerAuthentication() {
        Organization org = new Organization();
        org.setName("YabaTech");
        org.setCode("YAB001");
        org.setContactEmail("org@mail.com");
        org.setContactPhone("08012345678");
        org.setPassword("password");
        org.setRole("ORGANIZATION");

        var savedOrg = customerRepository.save(org);
        AuthenticatedPrincipal principal = new AuthenticatedPrincipal(savedOrg);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}