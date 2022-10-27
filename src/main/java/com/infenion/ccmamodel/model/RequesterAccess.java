package com.infenion.ccmamodel.model;

import javax.persistence.*;

@Entity
public class RequesterAccess extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Requester requester;

    @ManyToOne
    @JoinColumn(name = "system_access_id")
    private SystemAccess systemAccess;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    public SystemAccess getSystemAccess() {
        return systemAccess;
    }

    public void setSystemAccess(SystemAccess systemAccess) {
        this.systemAccess = systemAccess;
    }
}
