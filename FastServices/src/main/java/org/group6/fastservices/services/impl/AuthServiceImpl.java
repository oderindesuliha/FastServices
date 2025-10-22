package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.repositories.UserRepository;
import org.group6.fastservices.dtos.requests.LoginUserRequest;
import org.group6.fastservices.dtos.requests.RegisterUserRequest;
import org.group6.fastservices.dtos.responses.LoginUserResponse;
import org.group6.fastservices.dtos.responses.RegisterUserResponse;
import org.group6.fastservices.exceptions.DetailsAlreadyInUseException;
import org.group6.fastservices.services.AuthService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        return null;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        return null;
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new DetailsAlreadyInUseException("Email belongs to an existing account");
    }

    private void verifyNewPhone(String phone) {
        if (userRepository.existsByPhone(phone)) throw new DetailsAlreadyInUseException("Phone belongs to an existing account");
    }
}
