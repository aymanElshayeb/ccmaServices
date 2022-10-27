package com.infenion.ccmalogic.services;


import com.infenion.ccmadataservices.repositories.SystemAccessRepository;
import com.infenion.ccmamodel.model.SystemAccess;
import com.infenion.ccmamodel.model.SystemName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemAccessService {
    @Autowired
    SystemAccessRepository systemAccessRepository;
    public List<SystemAccess> findBySystemName(SystemName systemName){
        return  systemAccessRepository.findBySystemName(systemName);
    }

    public Optional<SystemAccess> findById(Long id) {
        return  systemAccessRepository.findById(id);
    }

    public List<SystemAccess> findAll() {
        return  systemAccessRepository.findAll();
    }
}
