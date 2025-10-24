package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.group6.fastservices.services.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfferingServiceImpl implements OfferingService {
    
    private final OfferingRepository offeringRepository;


    @Override
    public CreateServiceResponse createOffering(CreateServiceRequest request) {
        return null;
    }


}
