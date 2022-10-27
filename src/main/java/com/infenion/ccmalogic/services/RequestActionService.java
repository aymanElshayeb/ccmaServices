package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.ProjectRepository;
import com.infenion.ccmadataservices.repositories.RequestRepository;
import com.infenion.ccmadataservices.repositories.RequesterRepository;
import com.infenion.ccmadataservices.repositories.SystemAccessRepository;
import com.infenion.ccmamodel.model.*;
import com.sun.deploy.cache.CacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class RequestActionService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ExecutionService executionService;
    @Autowired
    private RequesterRepository requesterRepository;
    @Autowired
    private SystemAccessRepository systemAccessRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Request saveAsDraft(Request request) {
        request.setId(null);
        return changeStatusAndUpdate(request, Status.DRAFT,true);
    }

    public Request submit(Request request)  {
        return changeStatusAndUpdate(request, Status.PENDING, true);

    }

    public Request execute(Request request)  {
        try{
            executionService.execute(request);
            return changeStatusAndUpdate(request, Status.COMPLETED, true);
        } catch(Exception ex){
            return changeStatusAndUpdate(request, Status.PENDING, true);
        }

    }

    public Request changeStatusAndUpdate(Request request, Status status, boolean canInsert) {


        Optional<Request> newRequest = Optional.empty();
        if(canInsert){
            if(request.getId() !=null){
                newRequest =requestRepository.findById(request.getId());
            } else {
                newRequest= Optional.of(newRequest.orElseGet(()->getNewRequest()));
            }

        }

        Optional<Requester> requester = requesterRepository.findById(request.getRequester().getId());
        Optional<Requester>  lastModifier = requesterRepository.findById(request.getLastModifier().getId());
        Optional<SystemAccess> systemAccess= systemAccessRepository.findById(request.getSystemAccess().getId());
        Optional<Project> project = projectRepository.findById(request.getProject().getId());


        Optional<Request> finalNewRequest = newRequest;
        requester.ifPresent((value)-> finalNewRequest.get().setRequester(value));
        lastModifier.ifPresent((value)->finalNewRequest.get().setLastModifier(value));
        systemAccess.ifPresent((value)->finalNewRequest.get().setSystemAccess(value));
        project.ifPresent((value)->finalNewRequest.get().setProject(value));
        if(status !=null){
            newRequest.get().setStatus(status);
        }
        newRequest.get().setLastModifiedDate(Calendar.getInstance().getTime());
        return requestRepository.save(newRequest.get());
    }

    private Request getNewRequest() {
        Request request= new Request();
        request.setCreationDate(Calendar.getInstance().getTime());
        return request;
    }

    public Request returnToRequester(Request request) {
        return changeStatusAndUpdate(request, Status.DRAFT, true);
    }
}
