package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationRequest {
    private String name;
    private String password;
    private String address;
    private String contactEmail;
    private String contactPhone;
}