package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findAdminByEmail(String email);
}
