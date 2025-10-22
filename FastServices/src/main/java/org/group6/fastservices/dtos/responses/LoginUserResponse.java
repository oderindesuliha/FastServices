package org.group6.fastservices.dtos.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LoginUserResponse {

    private String message;
    private boolean success;
}
