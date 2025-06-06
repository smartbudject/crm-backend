package ru.smartbudject.crmbackend.config.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Конфиг для логов про сборку.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class InitInfo implements CommandLineRunner {


    private final InitInfoProperties initInfoProperties;


    /**
     * Метод для логов про сборку.
     */
    @Override
    public void run(final String... args) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("\uD83D\uDCE6 App Version: {}", initInfoProperties.getAppVersion());
            log.info("⏱\uFE0F Build Time: {}", initInfoProperties.getAppBuildTime());
        }
    }

}
