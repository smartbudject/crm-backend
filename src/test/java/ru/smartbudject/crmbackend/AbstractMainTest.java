package ru.smartbudject.crmbackend;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.Getter;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractMainTest {

    @ServiceConnection
    private static final PostgreSQLContainer<?> postgresqlContainer;


    /**
     * Web application context.
     */
    @Autowired
    protected WebApplicationContext ctx;


    /**
     * Mock mvc.
     */
    protected MockMvc mockMvc;

    /**
     * Object mapper.
     */
    @Autowired
    @Getter
    protected ObjectMapper mapper;


    /**
     * Create mock mvc.
     */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }


    @BeforeAll
    public void init() {

    }


    static {
        postgresqlContainer = new PostgreSQLContainer("postgres:13.16-alpine");
        postgresqlContainer.start();
    }

}
