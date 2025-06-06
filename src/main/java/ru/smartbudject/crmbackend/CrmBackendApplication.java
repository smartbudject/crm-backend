package ru.smartbudject.crmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка запуска приложения.
 */
@SpringBootApplication
public class CrmBackendApplication {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(CrmBackendApplication.class, args);
    }

}
