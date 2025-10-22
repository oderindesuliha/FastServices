package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.repositories.AdminRepository;
import org.group6.fastservices.data.repositories.CustomerRepository;
import org.group6.fastservices.data.repositories.UserRepository;
import org.group6.fastservices.dtos.requests.RegisterUserRequest;
import org.group6.fastservices.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
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
    }

    @Test
    void testCanRegisterUser() {
        registerAdmin();
        assertEquals(response);
    }

    private void registerCustomer() {
    RegisterUserRequest registerRequest = new RegisterUserRequest();
    registerRequest.setFirstName("Bram");
    registerRequest.setLastName("Dell");
    registerRequest.setEmail("bramtechxxvi@gmail.com");
    registerRequest.setPassword("password");
    registerRequest.setPhone("08012345678");
    registerRequest.setRole("customer");

    RegisterUserResponse response = authService.register(registerRequest);
}

private void registerAdmin() {
    RegisterUserRequest registerRequest = new RegisterUserRequest();
    registerRequest.setFirstName("Ola");
    registerRequest.setLastName("Dell");
    registerRequest.setEmail("niceibrahim01@gmail.com");
    registerRequest.setPassword("password");
    registerRequest.setPhone("08012345688");
    registerRequest.setRole("admin");

    RegisterUserResponse response = authService.register(registerRequest);

}
  
}