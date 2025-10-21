package org.group6.fastservices;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FastServicesApplicationTests {

    @Value("${TEST_DB_PASSWORD}")
    private String password;

    @Test
    void contextLoads() {
    }

    @Test
    void testCanConnectToDatabase() {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/fastservice");
            dataSource.setUsername("postgres");
            dataSource.setPassword(password);

            Connection connection = dataSource.getConnection();
            assertNotNull(connection);
        } catch(Exception e) {
            assertNotNull(e);
        }

    }

}
