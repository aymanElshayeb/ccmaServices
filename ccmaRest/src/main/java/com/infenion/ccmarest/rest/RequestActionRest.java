package com.infenion.ccmarest.rest;

import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmaservices.services.RequestActionService;
import com.infenion.ccmaservices.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
public class RequestActionRest {
    @Autowired
    private RequestActionService requestActionService;

    @PostMapping("/saveAsDraft/{requestId}")
    public void saveAsDraft(@PathVariable long requestId) {
         requestActionService.saveAsDraft( requestId);
    }
    @PostMapping("/submit/{requestId}")
    public void submit(@PathVariable long requestId) {
        requestActionService.submit( requestId);
    }
    @PostMapping("/execute/{requestId}")
    public void execute(@PathVariable long requestId) {
         requestActionService.execute(requestId);
    }
    @PostMapping("/returnToSender/{requestId}")
    public void returnToSender(@PathVariable long requestId){
         requestActionService.retunToSender(requestId);
    }
}