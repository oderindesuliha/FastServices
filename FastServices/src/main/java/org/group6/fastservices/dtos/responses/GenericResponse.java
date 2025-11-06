package org.group6.fastservices.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GenericResponse {
    private String message;
    private boolean success;
}
