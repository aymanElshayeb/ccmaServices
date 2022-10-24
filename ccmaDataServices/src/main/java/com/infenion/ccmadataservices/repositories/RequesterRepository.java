package com.infenion.ccmadataservices.repositories;


import com.infenion.ccmamodel.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RequesterRepository extends JpaRepository<Requester, Long> {
    public List<Requester> findAll();
    public List<Requester> findByUserNameContains(String userName);
    public Requester findByUserName(String userName);
    public Requester save(Requester requester);
}
