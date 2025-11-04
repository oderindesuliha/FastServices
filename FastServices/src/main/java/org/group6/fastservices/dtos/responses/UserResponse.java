package org.group6.fastservices.dtos.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

