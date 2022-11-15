package com.infenion;

import com.infenion.ccmaIntegeration.services.JiraExecutionService;
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
    JiraExecutionService executionService;
    @Test
    void contextLoads() {
    }

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
