package com.infenion.ccmarest.rest;

import com.atlassian.httpclient.api.HttpStatus;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmalogic.services.RequestActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Request execute(@RequestBody  Request request) throws Exception {
         return requestActionService.execute(request);
    }
    @PostMapping("/returnToRequester/")
    public Request returnToRequester(@RequestBody  Request request) throws MessagingException {
         return requestActionService.returnToRequester(request);
    }

    @GetMapping("/returnToRequesterFormMail/{request}")

    //todo :: return response entity
    public ResponseEntity<String> returnToRequester(@PathVariable  String request) throws MessagingException {
        requestActionService.returnToRequesterFromMail(Long.parseLong(request));
        return ResponseEntity.ok("request returned");
    }


    @GetMapping("/executeFromMail/{request}")
    public ResponseEntity<String> executeFromMail(@PathVariable  String request) {
        requestActionService.executeFromMail(Long.parseLong(request));
        return ResponseEntity.ok("request approved");
    }

}

