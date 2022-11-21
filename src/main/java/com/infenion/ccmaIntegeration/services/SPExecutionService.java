package com.infenion.ccmaIntegeration.services;


import com.infenion.ccmaIntegeration.model.SPRequester;
import com.infenion.ccmalogic.services.ProjectService;
import com.infenion.ccmalogic.services.RequesterService;
import com.infenion.ccmalogic.services.SystemAccessService;
import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import com.infenion.ccmamodel.model.SystemAccess;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Service
public class SPExecutionService implements ExecutionService {
    Logger logger = LoggerFactory.getLogger(SPExecutionService.class);

    @Value("${sp.url}")
    String spURL;
    @Value("${sp.username}")
    String spUserName;
    @Value("${sp.password}")
    String spPassword;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private RequesterService requesterService;
    @Autowired
    private SystemAccessService systemAccessService;

    public String execute(Request request) throws Exception {

        Requester requester = requesterService.findById(request.getRequester().getId()).get();
        Project project = projectService.findById(request.getProject().getId()).get();
        SystemAccess systemAccess = systemAccessService.findById(request.getSystemAccess().getId()).get();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(spUserName, spPassword);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        SPRequester[] requesterList = fetchUserId(requester);


        URI spURI = new URI(getAddRoleRequestURL(project, systemAccess));
        HttpEntity<SPRequester[]> requestEntity = new HttpEntity<>(requesterList, headers);

        ResponseEntity<String> response =restTemplate.exchange(spURI,  HttpMethod.POST, requestEntity, String.class);
        logger.info("Request Body "+ toString(requesterList) + "\n Response body"+ response.getBody());
        return response.getBody();
    }

    private String toString(SPRequester[] requesterList) {
        return Arrays.stream(requesterList).map(spRequester -> spRequester.toString()).reduce("",(substring,element)-> substring= substring +"\n"+ element );
    }

    private String getAddRoleRequestURL(Project project, SystemAccess systemAccess) {
        String url = String.format(spURL + "/applications/%s/instances/%s/projects/%s/roles/%s/users",
                project.getSpApplicationId(),
                project.getSpInstanceId(),
                project.getSpProjectId(),
                project.getSpRoleId(systemAccess.getAccessPermission()));
        return url;
    }

    private SPRequester[] fetchUserId(Requester requester) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(spUserName,spPassword);
        String spUrlRequest = String.format(spURL + "/ad/users?username=%s",requester.getUserName());
        URI spURI = new URI(spUrlRequest);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<SPRequester[]> response =restTemplate.exchange(spURI,  HttpMethod.GET, requestEntity, SPRequester[].class);
        if(response.getStatusCode().is2xxSuccessful()){
            SPRequester[] spRequesterList = response.getBody();
            return  spRequesterList;
        }
        return null;
    }


}
