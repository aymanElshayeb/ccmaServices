package com.infenion.ccmalogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.infenion.ccmadataservices.repositories"})
public class CcmaLogicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcmaLogicApplication.class, args);
    }

}
