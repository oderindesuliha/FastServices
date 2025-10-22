package org.group6.fastservices.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
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


@Override
public LoginUserResponse login(LoginUserRequest request) {
    // 1️⃣ Authenticate user credentials
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

    // 2️⃣ Set authentication context (optional but good practice)
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 3️⃣ Generate JWT token
    String token = jwtTokenProvider.generateToken(authentication);

    // 4️⃣ Retrieve authenticated user
    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

    // 5️⃣ Return response DTO
    return new LoginUserResponse(
            "Login successful",
            token,
            user.getEmail(),
            user.getRoles()
    );
}
