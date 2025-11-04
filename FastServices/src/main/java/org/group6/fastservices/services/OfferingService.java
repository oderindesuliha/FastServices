package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;

import java.util.List;

public interface OfferingService {
    CreateServiceResponse createOffering(CreateServiceRequest request);
//    Offering updateOffering(String id, Offering offering);


    Offering getOfferingById(String id);
//    List<Offering> getOfferingsByOrganizationId(String organizationId);
//    List<Offering> getAllOfferings();
//    void deleteOffering(String id);
}