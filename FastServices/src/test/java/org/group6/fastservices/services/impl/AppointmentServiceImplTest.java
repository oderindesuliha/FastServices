package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.data.models.AppointmentStatus;
import org.group6.fastservices.data.repositories.AppointmentRepository;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceImplTest {

    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
        appointmentRepository.deleteAll();

    }

    @AfterEach
    void tearDown() {
        appointmentRepository.deleteAll();
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

}