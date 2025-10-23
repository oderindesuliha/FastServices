package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.data.repositories.UserRepository;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;
import org.group6.fastservices.exceptions.DetailsAlreadyInUseException;
import org.group6.fastservices.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public RegisterOrgResponse registerOrg(RegisterOrgRequest register) {
        verifyNewEmail(register.getContactEmail());
        verifyNewPhone(register.getContactPhone());
        Role role = Role.ORGANIZATION;

        Organization org = modelMapper.map(register, Organization.class);
        org.setCode();
        org.setCreatedAt(LocalDateTime.now());

        return new RegisterOrgResponse();
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
