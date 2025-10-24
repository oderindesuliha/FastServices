package org.group6.fastservices.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomServiceResolver serviceResolver;
    private final PasswordEncoder passwordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameOrEmailorCode = authentication.getName();
        String password = authentication.getCredentials().toString();


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    private Role parseUserRole(String role) {
        try {
            return Role.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidRoleException("Invalid role: " + role);
        }
    }
}
