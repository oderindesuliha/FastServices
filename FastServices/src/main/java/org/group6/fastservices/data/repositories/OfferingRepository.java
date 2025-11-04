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
