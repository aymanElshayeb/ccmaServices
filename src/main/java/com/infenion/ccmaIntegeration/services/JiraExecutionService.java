package com.infenion.ccmaIntegeration.services;


import com.infenion.ccmalogic.services.ProjectService;
import com.infenion.ccmalogic.services.RequesterService;
import com.infenion.ccmalogic.services.SystemAccessService;
import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import com.infenion.ccmamodel.model.SystemAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class JiraExecutionService {
    @Value("${jira.url}")
    String jiraURL;
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
        map.add("user", requester.getUserName());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        URI jiraURI = new URI(String.format(jiraURL + "/project/%s/role/%s", project.getJiraId(), project.getJiraRoleId(systemAccess.getAccessPermission())));
        return restTemplate.postForObject(jiraURI, entity, String.class);
    }


}
