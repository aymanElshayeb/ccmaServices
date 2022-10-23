package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findAll();
    public Project findById(long id);
    public Project findByName(String name);
    public Project save(Project project);
}
