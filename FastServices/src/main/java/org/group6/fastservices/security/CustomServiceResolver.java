package org.group6.fastservices.security;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class CustomServiceResolver {
    private final Map<String, UserDetailsService> userDetailsServices;


    public UserDetailsService getServiceForRole(Role role) {
        return switch (role) {
            case CUSTOMER, ADMIN -> userDetailsServices.get("customUserDetailsService");
            case ORGANIZATION -> userDetailsServices.get("customOrganizationDetailsService");
            default -> throw new IllegalArgumentException("No UserDetailsService for role: " + role);
        };
    }
}
