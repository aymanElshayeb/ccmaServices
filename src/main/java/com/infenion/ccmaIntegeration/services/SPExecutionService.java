package com.infenion.ccmaIntegeration.services;


import com.infenion.ccmaIntegeration.model.SPRequester;
import com.infenion.ccmalogic.services.ProjectService;
import com.infenion.ccmalogic.services.RequesterService;
import com.infenion.ccmalogic.services.SystemAccessService;
import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import com.infenion.ccmamodel.model.SystemAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service("SPExecutionService")
public class SPExecutionService implements ExecutionService {
    @Value("${sp.url}")
    String spURL;
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
        headers.setBasicAuth(requester.getUserName(), requester.getPassword());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        String userId = fetchUserId(requester);
        map.add("id",userId );
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        URI jiraURI = new URI(String.format(spURL + "/applications/%s/instances/%s/projects/%s/roles/%s/users",
                project.getSpApplicationId(),
                project.getSpInstanceId(),
                project.getSpProjectId(),
                project.getSpRoleId(systemAccess.getAccessPermission())));

        return restTemplate.postForObject(jiraURI, entity, String.class);
    }

    private String fetchUserId(Requester requester) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(requester.getUserName(), requester.getPassword());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        URI spURI = new URI(String.format(spURL + "/rest/ad/users?username=%s",requester.getUserName()));
        SPRequester[] spRequesterList = restTemplate.getForObject(spURI, SPRequester[].class);
        return spRequesterList[0].getId();
    }


}
