package com.infenion.ccmalogic.services;


import com.infenion.ccmamodel.model.Request;
import org.springframework.stereotype.Service;

@Service
public class ExecutionService {
    public void execute(Request request) throws Exception{
        System.out.println("Executed Successfully "+ request);
    }
}
