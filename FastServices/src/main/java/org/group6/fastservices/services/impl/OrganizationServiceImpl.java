package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;
import org.group6.fastservices.exceptions.DetailsAlreadyInUseException;
import org.group6.fastservices.services.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.group6.fastservices.utils.OrganizationCodeGenerator.generateCode;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public RegisterOrgResponse registerOrganization(RegisterOrgRequest register) {
        verifyNewEmail(register.getContactEmail());
        verifyNewPhone(register.getContactPhone());

        Organization org = modelMapper.map(register, Organization.class);
        String code;
        do {
            code = generateCode();
        } while (organizationRepository.existsByCode(code));
        org.setCode(code);
        org.setPassword(passwordEncoder.encode(register.getPassword()));
        org.setCreatedAt(LocalDateTime.now());
        org.setRole(Role.ORGANIZATION.toString());

        organizationRepository.save(org);
        return new RegisterOrgResponse("Organization registered successfully", code, true);

    }

    private void verifyNewEmail(String email) {
        if (organizationRepository.existsByContactEmail(email))
            throw new DetailsAlreadyInUseException("Organization already exists");
    }

    private void verifyNewPhone(String phone) {
        if (organizationRepository.existsByContactPhone(phone))
            throw new DetailsAlreadyInUseException("Phone already exists");
    }
}