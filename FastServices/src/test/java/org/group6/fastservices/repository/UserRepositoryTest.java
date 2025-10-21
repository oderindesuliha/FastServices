package org.group6.fastservices.repository;

import org.group6.fastservices.data.models.User;
import org.group6.fastservices.data.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        Optional<User> foundUser = userRepository.findByEmail("chinedu.okonkwo@gmail.com");
        assertTrue(foundUser.isPresent());
        assertEquals("Chinedu", foundUser.get().getFirstName());
        assertEquals("Okonkwo", foundUser.get().getLastName());
    }

    @Test
    void testExistsByEmail() {
        boolean exists = userRepository.existsByEmail("adunni.alao@gmail.com");
        assertTrue(exists);
    }


}