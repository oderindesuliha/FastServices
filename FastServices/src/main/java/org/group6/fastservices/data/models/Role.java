package org.group6.fastservices.data.models;

public enum Role {
    ORGANIZATION,
    CUSTOMER,
    ADMIN
}



@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userService;
    private final CustomOrganizationDetailsService orgService;

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        UserDetails userDetails;

        // Role-based service selection
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (Exception e) {
            throw new InvalidRoleException("Invalid role: " + request.getRole());
        }

        switch (role) {
            case CUSTOMER, ADMIN -> userDetails = userService.loadUserByUsername(request.getEmail());
            case ORGANIZATION -> userDetails = orgService.loadUserByUsername(request.getCode());
            default -> throw new InvalidRoleException("Unsupported role for login");
        }

        // Authenticate using Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), request.getPassword())
        );

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(userDetails);

        return new LoginUserResponse("Login successful", token, true);
    }
}

