 package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.*;
import com.infenion.ccmamodel.model.*;
//import com.sun.deploy.cache.CacheEntry;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:application.properties")
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

    @Value("${emailFeatureActivation}")
    private Boolean emailFeatureActivation;
    public Request saveAsDraft(Request request)  {
        return changeStatusAndUpdate(request, Status.DRAFT,true);

    }

    public Request submit(Request request) throws MessagingException {

        Request r=changeStatusAndUpdate(request, Status.PENDING,true);
        return sendNotification(r,emailFeatureActivation);


    }

    private Request sendNotification(Request request,Boolean active) throws MessagingException {

        if (active) {
            if (request.getStatus() == Status.PENDING) {     //from requester to manager
                List<ProjectRole> managers = projectRoleRepository.findByProject(request.getProject());

                for (ProjectRole p : managers) {
                    if (p.getRole().toString() == "MANAGER") {
                        mailService.sendMail(p.getRequester().getEmail(), request);
                    }
                }
            } else if (request.getStatus() == Status.COMPLETED || request.getStatus() == Status.DRAFT) {    //from manager to requester
                mailService.sendMail(request.getRequester().getEmail(), request);
            }

        }
        return request;
    }
    public Request execute(Request request)  {
        try{
              System.out.println("execute ");
//            executionService.execute(request);
            Request r = changeStatusAndUpdate(request, Status.COMPLETED, false);
            return sendNotification(r,emailFeatureActivation);
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

    public Request returnToRequester(Request request) throws MessagingException {
        return changeStatusAndUpdate(sendNotification(request,emailFeatureActivation), Status.DRAFT, true);
    }
    public Request returnToRequesterFromMail(Long request) throws MessagingException {
        Request r=requestRepository.findById(request).get();
        r=changeStatusAndUpdate(r, Status.DRAFT, false);
        return sendNotification(r,emailFeatureActivation);
    }




    public Request executeFromMail(Long request)  {
        Request r=requestRepository.findById(request).get();
        try{
            System.out.println("execute "+request);
//            executionService.execute(request);
            r=changeStatusAndUpdate(r, Status.COMPLETED, false);
            return sendNotification(r,emailFeatureActivation);
        } catch(Exception ex){
            return changeStatusAndUpdate(r, Status.PENDING, false);
        }

    }
}


