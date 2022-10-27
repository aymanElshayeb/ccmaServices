package com.infenion.ccmarest.rest;

import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmalogic.services.RequestActionService;
import com.infenion.ccmalogic.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requestAction")
public class RequestActionRest {
    @Autowired
    private RequestActionService requestActionService;

    @PostMapping("/saveAsDraft/{requestId}")
    public void saveAsDraft(@PathVariable("requestId") long requestId) {
         requestActionService.saveAsDraft( requestId);
    }
    @PostMapping("/submit/{requestId}")
    public void submit(@PathVariable("requestId") long requestId) {
        requestActionService.submit( requestId);
    }
    @PostMapping("/execute/{requestId}")
    public void execute(@PathVariable("requestId") long requestId) {
         requestActionService.execute(requestId);
    }
    @PostMapping("/returnToSender/{requestId}")
    public void returnToSender(@PathVariable("requestId") long requestId){
         requestActionService.retunToSender(requestId);
    }
}