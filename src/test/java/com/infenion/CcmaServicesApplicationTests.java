package com.infenion;

import com.infenion.ccmalogic.services.ExecutionService;
import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import com.infenion.ccmamodel.model.SystemAccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CcmaServicesApplicationTests {

    @Autowired
    ExecutionService executionService;
    @Test
    void contextLoads() {
    }
    @Test
    void addWriteAccessJiraTest() throws Exception {
        Request request = new Request();
        Project project = new Project();
        Requester requester = new Requester();
        SystemAccess systemAccess = new SystemAccess();
        systemAccess.setId(2l);
        requester.setId(2l);
        request.setId(1l);
        project.setId(1l);
        request.setProject(project);
        request.setSystemAccess(systemAccess);
        request.setRequester(requester);
        executionService.execute(request);
    }



}
