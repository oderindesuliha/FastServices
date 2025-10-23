package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateServiceRequest {
    private String name;
    private String description;
    private int estimatedWaitTime;
    private int duration;
}


//Organization org = organizationRepository.findByContactEmail(orgEmail)
//        .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
//
//Offering offering = new Offering();
//    offering.setName(request.getName());
//        offering.setDescription(request.getDescription());
//        offering.setEstimatedWaitTime(request.getEstimatedWaitTime());
//        offering.setDuration(request.getDuration());
//        offering.setOrganization(org);
//
//    offeringRepository.save(offering);
//
//    return new CreateServiceResponse("Service created successfully", true);