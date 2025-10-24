package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OfferingServiceImplTest {

    @Autowired
    private OfferingServiceImpl offeringService;
    private OrganizationRepository organizationRepository;
    private OfferingRepository offeringRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @WithMockUser(roles = {"ORGANIZATION"})
    void testThatOrgCanCreateService() {
        CreateServiceResponse response = createService();
        assertTrue(response.isSuccess());
        assertEquals("Undergraduate clearance", response.getName());
    }



    private CreateServiceResponse createService() {
        CreateServiceRequest createService = new CreateServiceRequest();
        createService.setName("Undergraduate clearance");
        createService.setDescription("Requesting admission clearance for registration");
        createService.setEstimatedWaitTime(20);
        createService.setDuration(40);

        return offeringService.createOffering(createService);
    }

}