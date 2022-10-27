package com.infenion.ccmarest.rest;


import com.infenion.ccmamodel.model.SystemAccess;
import com.infenion.ccmalogic.services.SystemAccessService;
import com.infenion.ccmamodel.model.SystemName;
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

    @GetMapping("/{systemName}")
    public List<SystemAccess> getSystemAccess(@PathVariable("systemName") String systemName) {
        return systemAccessService.findBySystemName( SystemName.getEnum(systemName));
    }

    @GetMapping("/")
    public List<SystemAccess> findAll() {
        return systemAccessService.findAll();
    }
}
