package com.infenion.ccmarest.rest;

import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmalogic.services.RequestActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/requestAction")
public class RequestActionRest {
    @Autowired
    private RequestActionService requestActionService;

    @PostMapping("/saveAsDraft/")
    public Request saveAsDraft(@RequestBody  Request request) throws MessagingException {
         return requestActionService.saveAsDraft( request);
    }
    @PostMapping("/submit/")
    public Request submit(@RequestBody  Request request) throws MessagingException {

        return requestActionService.submit( request);
    }
    @PostMapping("/execute/")
    public Request execute(@RequestBody  Request request) {
         return requestActionService.execute(request);
    }
    @PostMapping("/returnToRequester/")
    public Request returnToRequester(@RequestBody  Request request){
         return requestActionService.returnToRequester(request);
    }
}