package org.group6.fastservices.dtos.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterOrgResponse {

    private String message;
    private String code;
    private boolean success;
}
