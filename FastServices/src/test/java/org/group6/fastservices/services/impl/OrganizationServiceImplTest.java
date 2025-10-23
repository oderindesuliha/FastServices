package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;
import org.group6.fastservices.services.OrganizationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationServiceImplTest {

    @Autowired
    private OrganizationServiceImpl orgService;
    @Autowired
    private OrganizationRepository orgRepository;

    @BeforeEach
    void setUp() {
        orgRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        orgRepository.deleteAll();
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void testCanRegisterOrganization() {
        RegisterOrgResponse response = registerOrganization();
        String code = response.getCode();
        Optional<Organization> savedOrg = orgRepository.findByCode(code);
        System.out.println(code);
        assertTrue(savedOrg.isPresent());
        assertEquals("YabaTech", savedOrg.get().getName());
        assertTrue(response.isSuccess());
    }

    @Test
    void testThatOrgCanCreateService() {

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