package org.group6.fastservices.repository;

import org.group6.fastservices.data.models.Customer;
import org.group6.fastservices.data.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/db/data.sql")
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testCanFindByEmail() {
        Optional<Customer> foundCustomer = customerRepository.findByEmail()
    }
}
