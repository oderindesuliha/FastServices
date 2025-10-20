package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, String> {
    List<Queue> findByOrganizationId(String organizationId);
    List<Queue> findByAppointmentsOfferingId(String offeringId);
}