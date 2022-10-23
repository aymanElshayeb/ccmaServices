package com.infenion.ccmarest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.infenion.ccmadataservices", "com.infenion.ccmadataservices"})
public class CcmaRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcmaRestApplication.class, args);
    }

}
