package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.LoginRequest;
import org.group6.fastservices.dtos.requests.RegisterUserRequest;
import org.group6.fastservices.dtos.responses.LoginResponse;
import org.group6.fastservices.dtos.responses.RegisterUserResponse;

public interface AuthService {

    RegisterUserResponse register(RegisterUserRequest request);

    LoginResponse login(LoginRequest request);
}


