package com.infenion.ccmalogic.services;

import com.infenion.ccmadataservices.repositories.ProjectRepository;
import com.infenion.ccmamodel.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public List<Project> findAll(){
        return  projectRepository.findAll();
    }
    public Project findById(long id){
        return  projectRepository.findById(id);
    }
}
