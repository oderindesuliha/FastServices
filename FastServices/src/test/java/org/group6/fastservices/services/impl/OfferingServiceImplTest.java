package org.group6.fastservices.services.impl;

import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OfferingServiceImplTest {

    @Autowired
    private OfferingServiceImpl offeringService;



    private CreateServiceResponse createService() {
        CreateServiceRequest createService = new CreateServiceRequest();
        createService.setName("Undergraduate clearance");
        createService.setDescription("Requesting admission clearance for registration");
        createService.setEstimatedWaitTime(20);
        createService.setDuration(40);

        return offeringService.createOffering(createService);
    }

}