package org.group6.fastservices.services.impl;

import jakarta.mail.internet.MimeMessage;
import org.group6.fastservices.data.models.Admin;
import org.group6.fastservices.data.models.User;
import org.group6.fastservices.data.repositories.*;
import org.group6.fastservices.dtos.requests.LoginRequest;
import org.group6.fastservices.dtos.requests.RegisterUserRequest;
import org.group6.fastservices.dtos.responses.LoginResponse;
import org.group6.fastservices.dtos.responses.RegisterUserResponse;
import org.group6.fastservices.security.CustomOrganizationDetailsService;
import org.group6.fastservices.security.CustomUserDetailsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @MockitoBean
    JavaMailSender javaMailSender;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    private CustomOrganizationDetailsService customOrganizationDetailsSer;
    @MockitoBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        customerRepository.deleteAll();
        adminRepository.deleteAll();
    }
    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        customerRepository.deleteAll();
        adminRepository.deleteAll();
        doNothing().when(javaMailSender).send(any(MimeMessage.class));
    }

    @Test
    void testCanRegisterUser() {
        RegisterUserResponse customerRegisterResponse = registerCustomer();
        assertTrue(customerRegisterResponse.isSuccess());
        Optional<User> savedCustomer = userRepository.findByEmail("bramtechxxvi@gmail.com");
        assertTrue(savedCustomer.isPresent());
        assertEquals("Bram", savedCustomer.get().getFirstName());

        RegisterUserResponse adminRegisterResponse = registerAdmin();
        assertTrue(adminRegisterResponse.isSuccess());
        Optional<Admin> savedAdmin = adminRepository.findAdminByEmail("niceibrahim01@gmail.com");
        assertTrue(savedAdmin.isPresent());
        assertEquals("Ola", savedAdmin.get().getFirstName());
    }

    @Test
    void testCanLoginUser() {
        registerCustomer();
        LoginRequest loginCustomerReq = new LoginRequest();
        loginCustomerReq.setIdentifier("bramtechxxvi@gmail.com");
        loginCustomerReq.setPassword("password");
        loginCustomerReq.setRole("CUSTOMER");

        LoginResponse loginCustomerResp = authService.login(loginCustomerReq);
        assertTrue(loginCustomerResp.isSuccess());
    }

    private RegisterUserResponse registerCustomer() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("Bram");
        registerRequest.setLastName("Dell");
        registerRequest.setEmail("bramtechxxvi@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setPhone("08012345678");
        registerRequest.setRole("customer");

        return authService.register(registerRequest);
    }

    private RegisterUserResponse registerAdmin() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("Ola");
        registerRequest.setLastName("Dell");
        registerRequest.setEmail("niceibrahim01@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setPhone("08012345688");
        registerRequest.setRole("admin");

        return authService.register(registerRequest);
    }
}