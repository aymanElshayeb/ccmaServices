package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    public Request findById(long id);

    public List<Request> findAll();

    public Request save(Request request);
}
