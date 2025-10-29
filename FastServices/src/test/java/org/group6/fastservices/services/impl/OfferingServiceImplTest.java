package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.CreateServiceRequest;
import org.group6.fastservices.dtos.responses.CreateServiceResponse;
import org.group6.fastservices.security.AuthenticatedPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Tra
class OfferingServiceImplTest {

    @Autowired
    private OfferingServiceImpl offeringService;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OfferingRepository offeringRepository;

    @BeforeEach
    void setUp() {
        organizationRepository.deleteAll();
        offeringRepository.deleteAll();
        mockOrganizationAuthentication();
    }

    @AfterEach
    void tearDown() {
        organizationRepository.deleteAll();
        offeringRepository.deleteAll();
        SecurityContextHolder.clearContext();
    }

    @Test
    void testThatOrgCanCreateService() {
        CreateServiceResponse response = createService();
        assertTrue(response.isSuccess());
        assertEquals("Undergraduate clearance", response.getName());

        Optional<Offering> savedService = offeringRepository.findOfferingByName("Undergraduate clearance");
    }

    private CreateServiceResponse createService() {
        CreateServiceRequest createService = new CreateServiceRequest();
        createService.setName("Undergraduate clearance");
        createService.setDescription("Requesting admission clearance for registration");
        createService.setEstimatedWaitTime(20);
        createService.setDuration(40);

        return offeringService.createOffering(createService);
    }

    private void mockOrganizationAuthentication() {
        Organization org = new Organization();
        org.setName("YabaTech");
        org.setCode("YAB001");
        org.setContactEmail("org@mail.com");
        org.setContactPhone("08012345678");
        org.setPassword("password");
        org.setRole("ORGANIZATION");

        var savedOrg = organizationRepository.save(org);
        AuthenticatedPrincipal principal = new AuthenticatedPrincipal(savedOrg);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}