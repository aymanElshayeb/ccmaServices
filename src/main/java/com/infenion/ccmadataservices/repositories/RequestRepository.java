package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.Project;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Requester;
import com.infenion.ccmamodel.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Set;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequester(Requester requester);

    List<Request> findByProjectInAndRequesterNotAndStatusNot(Set<Project> projectList, Requester currentRequester, Status status);
}
