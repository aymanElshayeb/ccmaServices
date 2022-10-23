package com.infenion.ccmarest.rest;


import com.infenion.ccmamodel.model.SystemAccess;
import com.infenion.ccmaservices.services.SystemAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/systemAccess")
public class SystemAccessRest {
    @Autowired
    private SystemAccessService systemAccessService;

    @GetMapping("/{systemId}")
    public List<SystemAccess> getSystemAccess(@PathVariable String systemId) {
        return systemAccessService.getSystemAccess(systemId);
    }
}
