package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.data.repositories.QueueRepository;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;
import org.group6.fastservices.services.QueueService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueueServiceImplTest {

    @Autowired
    private QueueService queueService;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OfferingRepository offeringRepository;

    private Organization organization;
    private Offering offering;
    private CreateQueueRequest request;

    @BeforeEach
    void setUp() {
        organization = new Organization();
        organization.setName("FAST Services");
        organization.setCode("FS001");
        organization.setContactEmail("contact@fake.com");
        organization.setContactPhone("1234567890");
        organization.setPassword("password");
        organization.setRole("ORGANIZATION");
        organization = organizationRepository.save(organization);

        offering = new Offering();
        offering.setName("Student Registration");
        offering.setDescription("Service for registering new students");
        offering.setOrganization(organization);
        offering = offeringRepository.save(offering);

        request = new CreateQueueRequest(
                offering.getId(),
                offering.getName(),
                organization.getId(),
                organization.getName()
        );
    }

    @Test
    void testFindOrCreateQueue_createsNewQueue() {
        CreateQueueResponse response = queueService.findOrCreateQueueForOffering(request);

        assertNotNull(response);
        assertTrue(response.isNewlyCreated());
        assertEquals("Student Registration Queue", response.getName());
        assertEquals("Queue for Student Registration", response.getDescription());
        assertEquals(organization.getId(), response.getOrganizationId());
        assertEquals(offering.getId(), response.getOfferingId());
    }

    @Test
    void testFindOrCreateQueue_reusesExistingQueue() {
        CreateQueueResponse first = queueService.findOrCreateQueueForOffering(request);
        CreateQueueResponse second = queueService.findOrCreateQueueForOffering(request);

        assertEquals(first.getId(), second.getId());
        assertFalse(second.isNewlyCreated());
        assertEquals(1, queueRepository.findAll().size());
    }
}
