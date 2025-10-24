package org.group6.fastservices.data.models;

public enum Role {
    ORGANIZATION,
    CUSTOMER,
    ADMIN
}

package org.group6.fastservices.security;

import lombok.RequiredArgsConstructor;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.exceptions.InvalidRoleException;
import org.springframework.security.authentication.*;
        import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomServiceResolver resolver;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String emailOrUsernameOrCode = authentication.getName();
        String password = authentication.getCredentials().toString();
        String roleFromRequest = authentication.getDetails().toString(); // ✅ Must be passed in

        Role role = parseUserRole(roleFromRequest);
        UserDetailsService userService = resolver.getServiceForRole(role);

        UserDetails user = userService.loadUserByUsername(emailOrUsernameOrCode);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );
    }

    private Role parseUserRole(String role) {
        try {
            return Role.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidRoleException("Invalid role: " + role);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}







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

