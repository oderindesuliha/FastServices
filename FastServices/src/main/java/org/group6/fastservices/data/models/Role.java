package org.group6.fastservices.data.models;

public enum Role {
    ORGANIZATION,
    CUSTOMER,
    ADMIN
}

package org.group6.fastservices.security;

import lombok.RequiredArgsConstructor;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.*;
        import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomServiceResolver serviceResolver;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameOrEmailOrCode = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        // Temporary: default login always as CUSTOMER if no prefix given
        Role role = Role.CUSTOMER;

        UserDetailsService userDetailsService = serviceResolver.getServiceForRole(role);
        UserDetails userDetails = userDetailsService.loadUserByUsername(usernameOrEmailOrCode);

        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

