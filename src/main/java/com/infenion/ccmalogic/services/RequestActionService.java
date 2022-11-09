 package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.*;
import com.infenion.ccmamodel.model.*;
//import com.sun.deploy.cache.CacheEntry;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RequestActionService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ExecutionService executionService;
    @Autowired
    MailService mailService;
    @Autowired
    private RequesterRepository requesterRepository;
    @Autowired
    private SystemAccessRepository systemAccessRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private  ProjectRoleRepository projectRoleRepository;



    public Request saveAsDraft(Request request) throws MessagingException {
        return changeStatusAndUpdate(request, Status.DRAFT,true);

        Request r=changeStatusAndUpdate(request, Status.DRAFT,true);

        return getRequest(r);
    }

    public Request submit(Request request) throws MessagingException {

        Request r=changeStatusAndUpdate(request, Status.PENDING,false);
        return sendNotification(r);

    }

    private Request sendNotification(Request request) throws MessagingException {
        List<ProjectRole> managers= projectRoleRepository.findByProject(request.getProject());

        for (ProjectRole p :managers) {
            if (p.getRole().toString()=="MANAGER") {
                mailService.sendMail(p.getRequester().getEmail(),request);
            }
        }
        return request;
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
    public Request returnToRequesterFromMail(Long request) {
        Request r=requestRepository.findById(request).get();
        return changeStatusAndUpdate(r, Status.DRAFT, false);
    }




    public Request executeFromMail(Long request)  {
        Request r=requestRepository.findById(request).get();
        try{
            executionService.execute(r);
            return changeStatusAndUpdate(r, Status.COMPLETED, false);
        } catch(Exception ex){
            return changeStatusAndUpdate(r, Status.PENDING, false);
        }

    }
}


