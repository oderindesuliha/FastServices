package org.group6.fastservices.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender mailSender() {
        return new JavaMailSenderImpl();
    }
}


@Override
public LoginResponse login(LoginRequest request) {
    // Parse role (optional: for JWT)
    Role role = parseUserRole(request.getRole());

    // Let AuthenticationManager handle user loading and password check
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getIdentifier(),  // email/username
                    request.getPassword()     // raw password
            )
    );

    // Set the authentication context
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Generate JWT with role info from authorities
    String token = jwtTokenProvider.generateToken(authentication);

    return new LoginResponse("Logged in successfully", token, true);
}


