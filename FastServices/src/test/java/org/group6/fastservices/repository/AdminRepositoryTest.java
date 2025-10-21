package org.group6.fastservices.repository;

import org.group6.fastservices.data.models.Admin;
import org.group6.fastservices.data.repositories.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/db/data.sql")
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void testFindByEmail() {
        Optional<Admin> foundAdmin = adminRepository.findAdminByEmail("chinedu.okonkwo@gmail.com");
        boolean isAdmin = foundAdmin.get().getRoles().toLowerCase().contains("admin");
        assertTrue(isAdmin);
        assertEquals("Chinedu", foundAdmin.get().getFirstName());
    }
}
