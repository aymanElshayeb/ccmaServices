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
        return changeStatusAndUpdate(request, Status.DRAFT,true);
    }

    public Request submit(Request request)  {
        return changeStatusAndUpdate(request, Status.PENDING, false);

    }

    public Request execute(Request request)  {
        try{
            executionService.execute(request);
            return changeStatusAndUpdate(request, Status.COMPLETED, false);
        } catch(Exception ex){
            return changeStatusAndUpdate(request, Status.PENDING, false);
        }

    }

    public Request changeStatusAndUpdate(Request request, Status status, boolean canInsert) {

        Optional<Request> newRequest = Optional.empty();
        Optional<Requester> requester =Optional.empty();
        Optional<Requester>  lastModifier = Optional.empty();
        Optional<SystemAccess> systemAccess= Optional.empty();
        Optional<Project> project = Optional.empty();


            if(request.getId() !=null){
                newRequest =requestRepository.findById(request.getId());
            } else if(canInsert){
                newRequest= Optional.of(newRequest.orElseGet(()->getNewRequest()));
            }

        if(request.getRequester()!=null ){
            requester = requesterRepository.findById(request.getRequester().getId());
        }

        if(request.getLastModifier()!=null ){
            lastModifier = requesterRepository.findById(request.getLastModifier().getId());
        }
        if(request.getSystemAccess()!=null ){
            systemAccess= systemAccessRepository.findById(request.getSystemAccess().getId());
        }
        if(request.getProject()!=null ){
            project= projectRepository.findById(request.getProject().getId());
        }



        Optional<Request> finalNewRequest = newRequest;
        requester.ifPresent((value)-> finalNewRequest.get().setRequester(value));
        lastModifier.ifPresent((value)->finalNewRequest.get().setLastModifier(value));
        systemAccess.ifPresent((value)->finalNewRequest.get().setSystemAccess(value));
        project.ifPresent((value)->finalNewRequest.get().setProject(value));

        if(status !=null){
            finalNewRequest.get().setStatus(status);
        }
        finalNewRequest.get().setLastModifiedDate(Calendar.getInstance().getTime());
        return requestRepository.save(finalNewRequest.get());
    }

    private Request getNewRequest() {
        Request request= new Request();
        request.setCreationDate(Calendar.getInstance().getTime());
        return request;
    }

    public Request returnToRequester(Request request) {
        return changeStatusAndUpdate(request, Status.DRAFT, false);
    }
}
