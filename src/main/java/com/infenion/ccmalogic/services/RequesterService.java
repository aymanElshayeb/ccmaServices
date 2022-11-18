package com.infenion.ccmalogic.services;


import com.infenion.ccmadataservices.repositories.RequesterRepository;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import com.infenion.ccmamodel.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Requester> findById(Long id) {
        return requesterRepository.findById(id);
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

    public List<Requester> findCurrentUserRequesterList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Requester requester = requesterRepository.findByUserName(auth.getName());
        List<Requester> requesterList = null;
        if(requester.getRole().equals(Role.MANAGER)){
            requesterList = requesterRepository.findAll();
        }else{
            requesterList = new ArrayList<>();
            requesterList.add(requester);
        }
        return requesterList;
    }

}
