package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;
import org.group6.fastservices.services.OrganizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationServiceImplTest {

    @Autowired
    private OrganizationServiceImpl orgService;
    @Autowired
    private OrganizationRepository orgRepository;

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void testCanRegisterOrganization() {
        RegisterOrgResponse response = registerOrganization();

        assertTrue(response.isSuccess());
    }

    private RegisterOrgResponse registerOrganization() {
        RegisterOrgRequest register = new RegisterOrgRequest();
        register.setName("YabaTech");
        register.setContactEmail("YabaTech@gmail.com");
        register.setContactPhone("07012345672");
        register.setPassword("yabatech");

        return orgService.registerOrganization(register);
    }

}