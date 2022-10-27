package com.infenion.ccmadataservices.repositories;

import com.infenion.ccmamodel.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
