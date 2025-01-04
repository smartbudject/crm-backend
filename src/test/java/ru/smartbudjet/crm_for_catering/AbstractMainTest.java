package ru.smartbudjet.crm_for_catering;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.smartbudjet.crm_for_catering.utils.testservices.DropDbTestService;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractMainTest {

    @ServiceConnection
    private static final PostgreSQLContainer<?> postgresqlContainer;

//    @ServiceConnection
//    private static final RabbitMQContainer rabbitMQContainer;


    /**
     * Web application context.
     */
    @Autowired
    protected WebApplicationContext ctx;

    @Autowired
    protected DropDbTestService dropDbTestService;

//    @MockBean
//    protected UserService userMockService;

    /**
     * Mock mvc.
     */
    protected MockMvc mockMvc;

    /**
     * Object mapper.
     */
    @Autowired
    @Getter
    private ObjectMapper mapper;


    /**
     * Create mock mvc.
     */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }


    @AfterEach
    public void tearDown() {
        dropDbTestService.dropAll();
    }


    static {
        postgresqlContainer = new PostgreSQLContainer("postgres:13.16-alpine");
        postgresqlContainer.start();
//        rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.13.7-alpine");
//        rabbitMQContainer.start();
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
