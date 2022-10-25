package com.infenion.ccmalogic.services;


import com.infenion.ccmadataservices.repositories.SystemAccessRepository;
import com.infenion.ccmamodel.model.SystemAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAccessService {
    @Autowired
    SystemAccessRepository systemAccessRepository;
    public List<SystemAccess> getSystemAccess(String systemId){
        return  systemAccessRepository.findBySystemID(systemId);
    }
}
