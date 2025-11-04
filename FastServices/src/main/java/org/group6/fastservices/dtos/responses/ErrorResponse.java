package org.group6.fastservices.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse<T> {
    private String error;
    
    public ErrorResponse(String error) {
        this.error = error;
    }
}