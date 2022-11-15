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
    public Request saveAsDraft(@RequestBody  Request request) {
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
    public Request returnToRequester(@RequestBody  Request request) throws MessagingException {
         return requestActionService.returnToRequester(request);
    }

    @GetMapping("/returnToRequesterFormMail/{request}")

    public void returnToRequester(@PathVariable  String request) throws MessagingException {
        requestActionService.returnToRequesterFromMail(Long.parseLong(request));
    }


    @GetMapping("/executeFromMail/{request}")
    public void executeFromMail(@PathVariable  String request) {

         requestActionService.executeFromMail(Long.parseLong(request));
    }

}

