package com.example.hospital.config;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Older schemas may define {@code users.role} as a MySQL ENUM that does not include all {@link com.example.hospital.entities.User.Role}
 * values, which causes "Data truncated for column 'role'" on insert. Widen to VARCHAR so all roles persist.
 */
@Component
@Order(1)
public class MysqlSchemaCompat implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MysqlSchemaCompat.class);
    private final JdbcTemplate jdbcTemplate;

    public MysqlSchemaCompat(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) {
        try {
            jdbcTemplate.execute("ALTER TABLE users MODIFY COLUMN role VARCHAR(32)");
            log.info("Ensured users.role is VARCHAR for role enum compatibility");
        } catch (Exception e) {
            log.debug("users.role compat alter skipped: {}", e.getMessage());
        }
    }
}
