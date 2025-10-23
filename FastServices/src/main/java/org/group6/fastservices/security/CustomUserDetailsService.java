package org.group6.fastservices.security;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.repositories.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findCustomerByEmail(usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}