package com.infenion.ccmarest.rest;

import com.infenion.ccmamodel.model.Project;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class AuthRest {
    @GetMapping("/login")
    public void login() {
        //this method is used to get session_id to access the remaining Rest service.
        //Spring boot security framework will handle the authentication and creating the session_id
    }
}
