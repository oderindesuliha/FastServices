package org.group6.fastservices.dtos.requests;

import lombok.Data;

@Data
public class QueueRequest {
    private String name;
    private String description;
    private String organizationId;
}


@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    // --------------------- Registration ---------------------

    // --------------------- Service Creation ---------------------
    @Override
    @PreAuthorize("hasRole('ORGANIZATION')")
    public CreateServiceResponse createService(CreateServiceRequest request) {
        Organization organization = getAuthenticatedOrganization();

        validateDuplicateServiceName(organization, request.getName());

        var service = mapToServiceEntity(request, organization);
        organization.getServices().add(service);
        organizationRepository.save(organization); // cascade persists service

        return new CreateServiceResponse(
                "Service created successfully",
                service.getName(),
                true
        );
    }

    // --------------------- Helper Methods ---------------------




    private void validateDuplicateServiceName(Organization org, String serviceName) {
        boolean exists = org.getServices().stream()
                .anyMatch(s -> s.getName().equalsIgnoreCase(serviceName));
        if (exists)
            throw new DetailsAlreadyInUseException("Service with name '" + serviceName + "' already exists");
    }

    private org.group6.fastservices.data.models.Service mapToServiceEntity(
            CreateServiceRequest request,
            Organization organization
    ) {
        var service = new org.group6.fastservices.data.models.Service();
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setOrganization(organization);
        service.setCreatedAt(LocalDateTime.now());
        return service;
    }
}
