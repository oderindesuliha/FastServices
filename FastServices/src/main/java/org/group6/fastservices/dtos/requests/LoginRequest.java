package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LoginRequest {

    private String identifier;
    private String password;
    private String role;
}
