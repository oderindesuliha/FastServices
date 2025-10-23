package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;


public interface OrganizationService {
    RegisterOrgResponse registerOrganization(RegisterOrgRequest registerRequest);
    CreateServiceResponse createService(CreateServiceRequest request);
//    Organization getOrganizationById(String id);
//    Organization getOrganizationByCode(String code);
//    List<Organization> getAllOrganizations();
//    Organization updateOrganization(String id, Organization organization);
//    void deleteOrganization(String id);
//    boolean existsByCode(String code);
}