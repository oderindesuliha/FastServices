package org.group6.fastservices.data.models;

public enum Role {
    ORGANIZATION,
    CUSTOMER,
    ADMIN
}


        // Authenticate using Spring Security
        authenticationManager.authenticate(
                new UsernamePas swordAuthenticationToken(userDetails.getUsername(), request.getPassword())
        );

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(userDetails);

        return new LoginUserResponse("Login successful", token, true);
    }
}

    // Determine which service to use based on type
    if ("ORGANIZATION".equalsIgnoreCase(request.getType())) {
        userDetails = orgService.loadUserByUsername(request.getIdentifier());
    } else {
        userDetails = userService.loadUserByUsername(request.getIdentifier());
    }

    // Authenticate
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), request.getPassword())
    );

    // Generate JWT including role claim
    String token = jwtTokenProvider.generateToken(userDetails);

    return new LoginUserResponse(
            "Logged in successfully",
            token,
            true
    );
}


