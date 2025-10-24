package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;

public interface OrganizationService {
    RegisterOrgResponse registerOrganization(RegisterOrgRequest registerRequest);
//    CreateServiceResponse createService(CreateServiceRequest request);
//    Organization getOrganizationById(String id);
//    List<Organization> getAllOrganizations();
//    Organization updateOrganization(String id, Organization organization);
//    void deleteOrganization(String id);
}


@Service
@RequiredArgsConstructor
public class OfferingServiceImpl implements OfferingService {

    private final OrganizationRepository organizationRepository;

    @Override
    @PreAuthorize("hasRole('ORGANIZATION')")
    public CreateServiceResponse createOffering(CreateServiceRequest request) {

        Organization organization = getAuthenticatedOrg();
        validateDuplicateServiceName(organization, request.getName());

        Offering offering = new Offering();
        offering.setName(request.getName());
        offering.setDescription(request.getDescription());
        offering.setPrice(request.getPrice());
        offering.setCreatedAt(LocalDateTime.now());
        offering.setOrganization(organization);

        organization.getServices().add(offering);
        organizationRepository.save(organization);

        return new CreateServiceResponse(
                "Service created successfully",
                offering.getName(),
                true
        );
    }

    private Organization getAuthenticatedOrg() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated())
            throw new RuntimeException("Unauthenticated access");

        Organization principal = (Organization) auth.getPrincipal();

        return organizationRepository.findByCode(principal.getCode())
                .orElseThrow(() -> new RuntimeException("Authenticated organization not found"));
    }

    private void validateDuplicateServiceName(Organization org, String serviceName) {
        boolean exists = org.getServices().stream()
                .anyMatch(service -> service.getName().equalsIgnoreCase(serviceName));
        if (exists)
            throw new DetailsAlreadyInUseException(
                    "Service with name '" + serviceName + "' already exists"
            );
    }
}
