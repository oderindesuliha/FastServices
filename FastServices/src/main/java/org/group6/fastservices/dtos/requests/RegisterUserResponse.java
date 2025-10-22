package org.group6.fastservices.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class RegisterUserResponse {
    private String message;
    private boolean success;
}
