package org.group6.fastservices.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginUserResponse {

    private String message;
    private String token;
    private boolean success;
}
