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
    Role role = parseUserRole(request.getRole());

    UserDetails userdetails = switch (role) {
        case CUSTOMER -> userService.loadUserByUsername(request.getIdentifier());
        case ADMIN -> adminRepository.findByEmail(request.getIdentifier())
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        case ORGANIZATION -> orgService.loadUserByUsername(request.getIdentifier());
    };

    // Check password manually
    if (!passwordEncoder.matches(request.getPassword(), userdetails.getPassword())) {
        throw new InvalidRoleException("Invalid credentials");
    }

    Authentication authentication = new UsernamePasswordAuthenticationToken(
            userdetails, null, userdetails.getAuthorities()
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtTokenProvider.generateToken(authentication);
    return new LoginResponse("Logged in successfully", token, true);
}
