package org.group6.fastservices.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RegisterOrgResponse {

    private String message;
    private String code;
    private boolean success;
}
