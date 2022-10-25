package com.infenion.ccmadataservices;

import com.infenion.ccmadataservices.repositories.RequestRepository;
import com.infenion.ccmadataservices.repositories.SystemAccessRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CcmaDataServicesApplicationTests {
    @Autowired
     private RequestRepository requestRepository;
    @Autowired
     private SystemAccessRepository systemAccessRepository;
    @Test
    void getRequestTest() {
        requestRepository.findAll().forEach(request -> {
            System.out.println(request);
        });
    }

    @Test
    void getSystemAccessTest() {
        systemAccessRepository.findAll().forEach(systemAccess -> {
            System.out.println(systemAccess);
        });
    }

}
