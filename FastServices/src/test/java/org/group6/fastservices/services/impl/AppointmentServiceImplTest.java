package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Customer;
import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.AppointmentRepository;
import org.group6.fastservices.data.repositories.CustomerRepository;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.CreateAppointmentRequest;
import org.group6.fastservices.dtos.responses.CreateAppointmentResponse;
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
    private OrganizationRepository organizationRepository;
    @Autowired
    private OfferingRepository offeringRepository;

    @BeforeEach
    void setUp() {
        appointmentRepository.deleteAll();
        customerRepository.deleteAll();
        organizationRepository.deleteAll();
        offeringRepository.deleteAll();
        mockCustomerAuthentication();
    }

    @AfterEach
    void tearDown() {
        appointmentRepository.deleteAll();
        customerRepository.deleteAll();
        organizationRepository.deleteAll();
        offeringRepository.deleteAll();
        SecurityContextHolder.clearContext();
    }

    @Test
    void testCanCreateAppointment() {
        CreateAppointmentResponse response  =  createAppointment();
        assertTrue(response.isSuccess());
        assertEquals("Native Registration", response.getOfferingName());
    }

    @Test
    void testCanGetAppointmentById() {
        CreateAppointmentResponse response = createAppointment();
        assertTrue(response.isSuccess());
    }

    private CreateAppointmentResponse createAppointment() {
        Offering offering = createOrganizationAndService();
        CreateAppointmentRequest request = new CreateAppointmentRequest();

        request.setOfferingId(offering.getId());
        request.setAppointmentDate(LocalDateTime.of(2025, 11, 16, 5, 30));
        return appointmentService.createAppointment(request);
    }

    private void mockCustomerAuthentication() {
        Customer customer = new Customer();
        customer.setFirstName("Alade");
        customer.setLastName("Jimoh");
        customer.setEmail("customer@mail.com");
        customer.setPhone("08012345678");
        customer.setPassword("password");
        customer.setRoles("CUSTOMER");

        var savedCustomer = customerRepository.save(customer);
        AuthenticatedPrincipal principal = new AuthenticatedPrincipal(savedCustomer);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Offering createOrganizationAndService() {
        Organization organization = new Organization();
        organization.setName("Semicolon");
        organization.setCode("SEM001");
        organization.setContactEmail("semicolon@fake.com");
        organization.setContactPhone("1234567890");
        organization.setPassword("password");
        organization.setRole("ORGANIZATION");
        organization = organizationRepository.save(organization);

        Offering offering = new Offering();
        offering.setName("Native Registration");
        offering.setOrganization(organization);
        return offeringRepository.save(offering);
    }
}