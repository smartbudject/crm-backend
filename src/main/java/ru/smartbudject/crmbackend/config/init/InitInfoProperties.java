package ru.smartbudject.crmbackend.config.init;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


/**
 * Информация об сборки проекта.
 */
@Component
@Data
@Configuration
@PropertySource("classpath:META-INF/build-info.properties")
public class InitInfoProperties {

    @Value("${build.version}")
    private String appVersion;

    @Value("${build.time}")
    private String appBuildTime;


    /**
     * Метод для получения даты.
     */
    public String getAppBuildTime() {
        final Instant instant = Instant.parse(appBuildTime);
        final LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

}
