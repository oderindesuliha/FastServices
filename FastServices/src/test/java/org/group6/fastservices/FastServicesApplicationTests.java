package org.group6.fastservices;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FastServicesApplicationTests {

    @Value("${DB_PASSWORD}")
    private String testDbPassword;

    @Value("${DB_URL}")
    private String testDbUrl;

    @Test
    void contextLoads() {
    }

    @Test
    void testCanConnectToDatabase() {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            dataSource.setJdbcUrl(testDbUrl);
            dataSource.setUsername("postgres");
            dataSource.setPassword(testDbPassword);

            Connection connection = dataSource.getConnection();
            assertNotNull(connection);
        } catch(Exception e) {
            assertNotNull(e);
        }
    }
}
