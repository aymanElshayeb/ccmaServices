package com.infenion.ccmarest.rest;


import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmaservices.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectRest {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/")
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }
    @GetMapping("/id")
    public Project getAllProjects(@PathVariable Long id) {
        return projectService.findById(id);
    }
}
