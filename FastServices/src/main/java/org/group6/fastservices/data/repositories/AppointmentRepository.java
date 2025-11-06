package org.group6.fastservices.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentResponse, String> {
    List<AppointmentResponse> findByUserId(String userId);
    List<AppointmentResponse> findByOfferingId(String offeringId);
    List<AppointmentResponse> findByQueueId(String queueId);

    int countByQueueId(String id);
}