package ru.smartbudject.crmbackend.config.init;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Котроллер для выдачи информации об сборке.
 */
@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
public class InitController {

    private final InitInfoProperties initInfoProperties;


    /**
     * Метод для выдачи информации об сборке.
     */
    @GetMapping
    public ResponseEntity<InitInfo> init() {
        return ResponseEntity.ok(new InitInfo(initInfoProperties.getAppVersion(), initInfoProperties.getAppBuildTime()));
    }


    private record InitInfo(String version, String buildTime) {

    }

}
