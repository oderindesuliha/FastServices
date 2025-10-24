package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;
import org.group6.fastservices.exceptions.DetailsAlreadyInUseException;
import org.group6.fastservices.services.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import static org.group6.fastservices.utils.OrganizationCodeGenerator.generateCode;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final OfferingRepository offeringRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public RegisterOrgResponse registerOrganization(RegisterOrgRequest register) {
        verifyNewEmail(register.getContactEmail());
        verifyNewPhone(register.getContactPhone());

        Organization org = modelMapper.map(register, Organization.class);

        org.setCode(generateUniqueOrgCode());
        org.setPassword(passwordEncoder.encode(register.getPassword()));
        org.setCreatedAt(LocalDateTime.now());
        org.setRole(Role.ORGANIZATION.name());

        Organization organization = organizationRepository.save(org);
        return new RegisterOrgResponse("Organization registered successfully", organization.getCode(), true);
    }

    @Override
    public CreateServiceResponse createService(CreateServiceRequest request) {
        Organization organization = getAuthenticatedOrg();
        validateDuplicateServiceName(organization, request.getName());

        Offering service = modelMapper.map(request, Offering.class);
        service.setOrganization(organization);
        service.setCreatedAt(LocalDateTime.now());
        organization.getServices().add(service);
        organizationRepository.save(organization);
        Offering offering = offeringRepository.save(service);

        return new CreateServiceResponse(offering.getName(), "Added successfully", true);
    }

    private void validateDuplicateServiceName(Organization org, String serviceName) {
        boolean exists = org.getServices().stream()
                .anyMatch(service -> service.getName().equals(serviceName));
        if (exists) throw new DetailsAlreadyInUseException("Service with name " + serviceName + " already exists");
    }

    private Organization getAuthenticatedOrg() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()) throw new RuntimeException("Unauthenticated access");

        var orgDetails = (Organization) auth.getPrincipal();
        return organizationRepository.findByCode(orgDetails.getCode())
                .orElseThrow(()-> new DetailsAlreadyInUseException("Authenticated organization not found"));
    }

    private void verifyNewEmail(String email) {
        if (organizationRepository.existsByContactEmail(email))
            throw new DetailsAlreadyInUseException("Organization already exists");
    }

    private void verifyNewPhone(String phone) {
        if (organizationRepository.existsByContactPhone(phone))
            throw new DetailsAlreadyInUseException("Phone already exists");
    }

    private String generateUniqueOrgCode() {
        String code;
        do {
            code = generateCode();
        } while (organizationRepository.existsByCode(code));
        return code;
    }
}