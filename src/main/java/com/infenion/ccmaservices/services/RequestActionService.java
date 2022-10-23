package com.infenion.ccmaservices.services;

import com.infenion.ccmadataservices.repositories.RequestRepository;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestActionService {
    @Autowired
    RequestRepository requestRepository;
    ExecutionService executionService;
    public void saveAsDraft(long requestId) {
        Request request= requestRepository.findById(requestId);
        updateStatusAndSave(request, Status.DRAFT);
    }

    public void submit(long requestId) {
        Request request= requestRepository.findById(requestId);
        updateStatusAndSave(request, Status.PENDING);

    }

    public void execute(long requestId) {
        Request request= requestRepository.findById(requestId);
        try{
            executionService.execute(request);
            updateStatusAndSave(request, Status.COMPLETED);
        } catch(Exception ex){
            updateStatusAndSave(request, Status.PENDING);
        }

    }

    private void updateStatusAndSave(Request request, Status completed) {
        request.setStatus(completed);
        requestRepository.save(request);
    }

    public void retunToSender(long requestId) {
        Request request= requestRepository.findById(requestId);
        updateStatusAndSave(request, Status.DRAFT);
    }
}
