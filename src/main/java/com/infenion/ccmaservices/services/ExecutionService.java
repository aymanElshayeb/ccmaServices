package com.infenion.ccmaservices.services;


import com.infenion.ccmadataservices.repositories.RequesterRepository;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutionService {
    public void execute(Request request) throws Exception{
        System.out.println("Executed Sucessfully "+ request);
    }
}
