package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.ProjectRoleRepository;
import com.infenion.ccmadataservices.repositories.RequestRepository;
import com.infenion.ccmamodel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    private RequesterService requesterService;
    @Autowired
    private SystemAccessService systemAccessService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    public Optional<Request> findById(Long requestId) {
        return requestRepository.findById(requestId);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request save(Request request) {
     return null;
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }

    public List<Request> findCurrentUserRequests() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Requester currentRequester = requesterService.findByUserName(auth.getName());
        Role userRole = currentRequester.getRole();
        List<Request> requestList = requestRepository.findByRequester(currentRequester);
        List<Request> allRequest = requestRepository.findAll();
        if(userRole.equals(Role.MANAGER)){
            List<ProjectRole> projectRoleList = projectRoleRepository.findByRequester(currentRequester);
            Set<Project> projectList = projectRoleList.stream().map(projectRole -> projectRole.getProject()).collect(Collectors.toSet());
            List<Request> requestsOfManagerProjects = requestRepository.findByProjectInAndRequesterNotAndStatusNot( projectList, currentRequester, Status.DRAFT);
            requestList.addAll(requestsOfManagerProjects);
        }

        return requestList;

    }
}
