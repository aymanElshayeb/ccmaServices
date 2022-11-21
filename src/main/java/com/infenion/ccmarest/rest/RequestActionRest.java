package com.infenion.ccmarest.rest;

import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmalogic.services.RequestActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/returnToRequesterFormMail/{requestId}")

    public ModelAndView returnToRequester(@PathVariable  String requestId, Model model) throws MessagingException {
        Request request=requestActionService.returnToRequesterFromMail(Long.parseLong(requestId));
        return bindRequestParameters(model, request);
    }

    private ModelAndView bindRequestParameters(Model model, Request request) {
        model.addAttribute("ProjectName", request.getProject().getName());
        model.addAttribute("accessPermission", request.getSystemAccess().getAccessPermission().toString());
        model.addAttribute("systemName",  request.getSystemAccess().getSystemName().toString());
        model.addAttribute("requestStatus",request.getStatus());
        model.addAttribute("requesterName", request.getRequester().getUserName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Confirmation");
        return modelAndView;
    }


    @GetMapping("/executeFromMail/{requestId}")
    public ModelAndView executeFromMail(@PathVariable  String requestId, Model model) {
        Request request=requestActionService.executeFromMail(Long.parseLong(requestId));
        return bindRequestParameters(model, request);
    }

}

