package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.SystemAccess;
import com.infenion.ccmamodel.model.SystemName;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface  SystemAccessRepository extends JpaRepository<SystemAccess, Long> {
    List<SystemAccess> findBySystemName(SystemName systemName);
}
