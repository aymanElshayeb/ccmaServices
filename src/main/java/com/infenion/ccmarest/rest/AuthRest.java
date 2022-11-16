package com.infenion.ccmarest.rest;

import com.infenion.ccmalogic.services.RequesterService;
import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmamodel.model.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
public class AuthRest {
    @Autowired
    RequesterService requesterService;
    @GetMapping("/login")
    public Requester login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return requesterService.findByUserName(auth.getName());
    }
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
