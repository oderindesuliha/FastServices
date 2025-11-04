package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferingRepository extends JpaRepository<Offering, String> {
    List<Offering> findByOrganizationId(String organizationId);
    Optional<Offering> findOfferingByName(String name);

}


@Autowired
private OfferingRepository offeringRepository;
@Autowired
private OrganizationRepository organizationRepository;

@Test
void testCanCreateAppointment() {
    // create org
    Organization org = new Organization();
    org.setName("Fast Services HQ");
    org = organizationRepository.save(org);

    // create offering
    Offering offering = new Offering();
    offering.setName("Document Pickup");
    offering.setOrganization(org);
    offering = offeringRepository.save(offering);

    // create appointment request
    CreateAppointmentRequest request = new CreateAppointmentRequest();
    request.setOfferingId(offering.getId());
    request.setAppointmentDate(LocalDateTime.of(2025, 11, 16, 5, 30));

    CreateAppointmentResponse response = appointmentService.createAppointment(request);

    assertTrue(response.isSuccess());
    assertEquals("Document Pickup", response.getOfferingName());
}

