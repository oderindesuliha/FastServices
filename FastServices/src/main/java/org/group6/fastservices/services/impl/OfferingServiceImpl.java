package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.group6.fastservices.exceptions.AccessDeniedException;
import org.group6.fastservices.exceptions.DetailsAlreadyInUseException;
import org.group6.fastservices.security.AuthenticatedPrincipal;
import org.group6.fastservices.services.OfferingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfferingServiceImpl implements OfferingService {
    
    private final OfferingRepository offeringRepository;
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;


    @Override
    @PreAuthorize("hasRole('ORGANIZATION')")
    public CreateServiceResponse createOffering(CreateServiceRequest request) {
        Organization organization = getAuthenticatedOrg();
        validateDuplicateServiceName(organization, request.getName());

        Offering service = modelMapper.map(request, Offering.class);
        service.setOrganization(organization);
        service.setCreatedAt(LocalDateTime.now());

        organization.getServices().add(service);
        organizationRepository.save(organization);
        return new CreateServiceResponse(service.getName(), "Added successfully", true);
    }

    private void validateDuplicateServiceName(Organization org, String serviceName) {
        boolean exists = org.getServices().stream()
                .anyMatch(service -> service.getName().equals(serviceName));
        if (exists) throw new DetailsAlreadyInUseException("Service with name " + serviceName + " already exists");
    }

    private Organization getAuthenticatedOrg() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()) throw new RuntimeException("Unauthenticated access");

        var orgDetails = (AuthenticatedPrincipal) auth.getPrincipal();
        if(!orgDetails.isOrganizationAccount()) throw new AccessDeniedException("Access not granted");
        return organizationRepository.findByCode(orgDetails.getUsername())
                .orElseThrow(()-> new DetailsAlreadyInUseException("Authenticated organization not found"));
    }
}
