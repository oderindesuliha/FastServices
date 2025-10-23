package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterOrgRequest {

    private String name;
    private String contactEmail;
    private String contactPhone;
    private String password;
}
