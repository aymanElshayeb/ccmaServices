package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmamodel.model.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRoleRepository extends JpaRepository<ProjectRole, Long> {

    List<ProjectRole> findByProject(Project project);
}