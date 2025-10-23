package org.group6.fastservices.security;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOrganizationDetailsService implements UserDetailsService {

    private final OrganizationRepository organizationRepository;
    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        return (UserDetails) organizationRepository.findByCode(code)
                .orElseThrow(()-> new UsernameNotFoundException("Organization not found"));
    }
}
