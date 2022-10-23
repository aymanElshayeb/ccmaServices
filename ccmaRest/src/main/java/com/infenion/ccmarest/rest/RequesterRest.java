package com.infenion.ccmarest.rest;


import com.infenion.ccmamodel.model.Requester;
import com.infenion.ccmaservices.services.RequesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requester")
public class RequesterRest {

    @Autowired
    private RequesterService requesterService;

    @GetMapping("/{requesterUserName}")
    public Requester findByUserName(@PathVariable String requesterUserName) {
        return requesterService.findByUserName(requesterUserName);
    }
    @GetMapping("/")
    public List<Requester> findAll() {
        return requesterService.findAll();
    }
    @GetMapping("/contains/{requesterUserName}")
    public List<Requester> getRequestersByUsernameContains(@PathVariable String requesterUserName) {
        return requesterService.findByUserNameContains(requesterUserName);
    }
    @PostMapping("/")
    public Requester save(Requester requester) {
        return requesterService.save(requester);
    }
    @PutMapping ("/")
    public Requester update(Requester requester) {
        return requesterService.update(requester);
    }

}
