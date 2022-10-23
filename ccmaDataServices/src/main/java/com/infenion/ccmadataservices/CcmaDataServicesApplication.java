package com.infenion.ccmadataservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.infenion.ccmamodel.model")
public class CcmaDataServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcmaDataServicesApplication.class, args);
    }

}
