package org.group6.fastservices.repository;

import org.group6.fastservices.data.models.Admin;
import org.group6.fastservices.data.repositories.AdminRepository;
import org.group6.fastservices.data.repositories.CustomerRepository;
import org.group6.fastservices.data.repositories.UserRepository;
import org.group6.fastservices.security.CustomOrganizationDetailsService;
import org.group6.fastservices.security.CustomUserDetailsService;
import org.group6.fastservices.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/db/data.sql")
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    private CustomOrganizationDetailsService customOrganizationDetailsService;
    @MockitoBean
    private JavaMailSender javaMailSender;
    @MockitoBean
    private AuthenticationManager authenticationManager;

    @Test
    void testFindByEmail() {
        Optional<Admin> foundAdmin = adminRepository.findAdminByEmail("chinedu.okonkwo@gmail.com");
        boolean isAdmin = foundAdmin.get().getRoles().toLowerCase().contains("admin");
        assertTrue(isAdmin);
        assertEquals("Chinedu", foundAdmin.get().getFirstName());
    }
}
