package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.RequestRepository;
import com.infenion.ccmamodel.model.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    RequestRepository requestRepository;
    public Request findById(long requestId) {
        return requestRepository.findById(requestId);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }
}
