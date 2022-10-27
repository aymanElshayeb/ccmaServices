package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.RequestRepository;
import com.infenion.ccmamodel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

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

    public Request findById(long requestId) {
        return requestRepository.findById(requestId);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request save(Request request) {
        Requester requester = requesterService.findById(request.getRequester().getId());
        SystemAccess systemAccess= systemAccessService.findById(request.getSystemAccess().getId());
        Project project = projectService.findById(request.getProject().getId());
//        request.setId(-1);
//        request.setRequester(requester);
//        request.setSystemAccess(systemAccess);
//        request.setProject(project);
//        request.setStatus(Status.DRAFT);
//        request.setLastModifier(requester);
//        request.setLastModifiedDate(Calendar.getInstance().getTime());
//        request.setCreationDate(Calendar.getInstance().getTime());
        return requestRepository.save(request);
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }
}
