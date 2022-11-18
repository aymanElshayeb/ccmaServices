package com.infenion.ccmarest.rest;

import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmalogic.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/request")
public class RequestRest {
    @Autowired
    private RequestService requestService;

    @GetMapping("/{id}")
    public Optional<Request> getRequest(@PathVariable("id") Long id) {
        return requestService.findById(id);
    }
    @GetMapping("/")
    public List<Request> getAllRequests() {
        return requestService.findCurrentUserRequests();
    }
    @PostMapping("/")
    public Request save(@RequestBody Request request) {
        return requestService.save(request);
    }
    @PutMapping ("/")
    public Request update(@RequestBody Request request) {
        return requestService.update(request);
    }
}
