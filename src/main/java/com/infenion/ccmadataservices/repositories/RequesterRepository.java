package com.infenion.ccmadataservices.repositories;


import com.infenion.ccmamodel.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RequesterRepository extends JpaRepository<Requester, Long> {
    List<Requester> findByUserNameContains(String requesterName);

    Requester findByUserName(String requesterName);
}
