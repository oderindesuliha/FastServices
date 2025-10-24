package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.services WHERE o.code = :code")
    Optional<Organization> findByCode(String code);


    boolean existsByCode(String code);
    boolean existsByContactEmail(String email);
    boolean existsByContactPhone(String phone);
}