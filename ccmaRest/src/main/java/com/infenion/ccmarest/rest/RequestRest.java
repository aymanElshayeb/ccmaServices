package com.infenion.ccmarest.rest;

import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmaservices.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestRest {
    @Autowired
    private RequestService requestService;

    @GetMapping("/{id}")
    public Request getRequest(@PathVariable long id) {
        return requestService.findById(id);
    }
    @GetMapping("/")
    public List<Request> getAllRequests() {
        return requestService.findAll();
    }
    @PostMapping("/")
    public Request save(Request request) {
        return requestService.save(request);
    }
    @PutMapping ("/")
    public Request update(Request request) {
        return requestService.update(request);
    }
}
