package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.RequestRepository;
import com.infenion.ccmamodel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    private RequesterService requesterService;
    @Autowired
    private SystemAccessService systemAccessService;
    @Autowired
    private ProjectService projectService;

    public Optional<Request> findById(Long requestId) {
        return requestRepository.findById(requestId);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request save(Request request) {
     return null;
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }
}
