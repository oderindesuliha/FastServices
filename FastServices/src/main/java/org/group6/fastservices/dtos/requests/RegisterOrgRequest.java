package org.group6.fastservices.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterOrgRequest {

    @NotBlank(message="Enter company name")
    private String name;
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
    private String contactEmail;
    @NotBlank
    @Pattern(regexp = "^0[7-9][0-1]\\d[- ]?\\d{3}[- ]?[0-9]{4}$", message = "Invalid phone")
    private String contactPhone;
    @NotBlank
    @Size(min = 8, max = 16, message = "Password must be between 8 to 16 characters")
    private String password;
}
