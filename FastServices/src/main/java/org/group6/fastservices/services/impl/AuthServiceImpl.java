package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Admin;
import org.group6.fastservices.data.models.Customer;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.models.User;
import org.group6.fastservices.data.repositories.*;
import org.group6.fastservices.dtos.requests.LoginRequest;
import org.group6.fastservices.dtos.requests.RegisterUserRequest;
import org.group6.fastservices.dtos.responses.LoginResponse;
import org.group6.fastservices.dtos.responses.RegisterUserResponse;
import org.group6.fastservices.exceptions.DetailsAlreadyInUseException;
import org.group6.fastservices.exceptions.InvalidRoleException;
import org.group6.fastservices.security.CustomOrganizationDetailsService;
import org.group6.fastservices.security.CustomUserDetailsService;
import org.group6.fastservices.security.JwtTokenProvider;
import org.group6.fastservices.services.AuthService;
import org.group6.fastservices.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userService;
    private final CustomOrganizationDetailsService orgService;


    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        verifyNewEmail(request.getEmail());
        verifyNewPhone(request.getPhone());
        Role userRole = parseUserRole(request.getRole());

        User user = switch(userRole){
            case ADMIN -> modelMapper.map(request, Admin.class);
            case CUSTOMER -> modelMapper.map(request, Customer.class);
            default -> throw new InvalidRoleException("Invalid role");
        };

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRoles(userRole.name());

        switch(userRole) {
            case ADMIN -> adminRepository.save((Admin) user);
            case CUSTOMER -> customerRepository.save((Customer) user);
        }

        if(user instanceof Admin) {
            adminRepository.save((Admin) user);
        } else {
            customerRepository.save((Customer ) user);
        }

        emailService.sendRegistrationEmail(user.getEmail(), user.getFirstName());
        return new RegisterUserResponse("Registered successfully", true);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UserDetails userdetails;
        Role role = parseUserRole(request.getRole());

        switch (role) {

            case CUSTOMER, ADMIN -> userdetails = userService.loadUserByUsername(request.getIdentifier());
            case ORGANIZATION -> userdetails = orgService.loadUserByUsername(request.getIdentifier());
            default -> throw new InvalidRoleException("Invalid role");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userdetails.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return new LoginResponse(
                "Logged in successfully",
                token,
                true
        );
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email))
            throw new DetailsAlreadyInUseException("Email belongs to an existing account");
    }

    private void verifyNewPhone(String phone) {
        if (userRepository.existsByPhone(phone))
            throw new DetailsAlreadyInUseException("Phone belongs to an existing account");
    }

    private Role parseUserRole(String role) {
        try {
            return Role.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidRoleException("Invalid role specified: " + role);
        }
    }
}
