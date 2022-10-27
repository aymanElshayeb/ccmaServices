package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.SystemAccess;
import com.infenion.ccmamodel.model.SystemName;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface  SystemAccessRepository extends JpaRepository<SystemAccess, Long> {
    public SystemAccess findById(long id);
    public List<SystemAccess> findAll();
    public List<SystemAccess> findBySystemName(SystemName systemName);

    public SystemAccess save(SystemAccess systemAccess);
}
