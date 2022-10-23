package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.SystemAccess;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface  SystemAccessRepository extends JpaRepository<SystemAccess, Long> {
    public List<SystemAccess> findAll();
    public List<SystemAccess> finBySystemID(String systemID);

    public SystemAccess save(SystemAccess systemAccess);
}
