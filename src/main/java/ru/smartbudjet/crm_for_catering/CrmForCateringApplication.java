package ru.smartbudjet.crm_for_catering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(scanBasePackages = {
		"ru.smartbudjet"
})
@EnableAsync
@EnableJpaRepositories
public class CrmForCateringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmForCateringApplication.class, args);
	}

}
