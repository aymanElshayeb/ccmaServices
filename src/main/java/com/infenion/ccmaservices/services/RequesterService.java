package com.infenion.ccmaservices.services;


import com.infenion.ccmadataservices.repositories.RequesterRepository;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequesterService {
    @Autowired
    RequesterRepository requesterRepository;
    public List<Requester> findAll(){
        return requesterRepository.findAll();
    }

    public Requester findByUserName(String requesterName) {
        return requesterRepository.findByUserName(requesterName);
    }
    public List<Requester> findByUserNameContains(String requesterName) {
        return requesterRepository.findByUserNameContains(requesterName);
    }

    public Requester save(Requester requester) {
        return requesterRepository.save(requester);
    }

    public Requester update(Requester requester) {
        return requesterRepository.save(requester);
    }
}
