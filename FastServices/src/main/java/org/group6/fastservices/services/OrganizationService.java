package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;


public interface OrganizationService {
    RegisterOrgResponse registerOrganization(RegisterOrgRequest registerRequest);
    CreateServiceResponse createService(CreateServiceRequest request);
//    Organization getOrganizationById(String id);
//    List<Organization> getAllOrganizations();
//    Organization updateOrganization(String id, Organization organization);
//    void deleteOrganization(String id);
}



@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http,
                                        CustomAuthenticationProvider authProvider,
                                        JwtAuthenticationFilter jwtFilter) throws Exception {

    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
            )
            .authenticationProvider(authProvider)
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
