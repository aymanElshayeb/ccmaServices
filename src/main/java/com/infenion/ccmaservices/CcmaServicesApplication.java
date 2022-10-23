package com.infenion.ccmaservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.infenion.ccmadataservices")
public class CcmaServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcmaServicesApplication.class, args);
    }

}
