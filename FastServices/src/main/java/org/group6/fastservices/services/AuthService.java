package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.LoginUserRequest;
import org.group6.fastservices.dtos.requests.RegisterUserRequest;
import org.group6.fastservices.dtos.responses.LoginUserResponse;
import org.group6.fastservices.dtos.responses.RegisterUserResponse;

public interface AuthService {

    RegisterUserResponse register(RegisterUserRequest request);
    LoginUserResponse login(LoginUserRequest request);
}
