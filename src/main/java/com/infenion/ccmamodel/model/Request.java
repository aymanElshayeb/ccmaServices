package com.infenion.ccmamodel.model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Request extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "requester_Id")
    private Requester requester;

    @ManyToOne
    @JoinColumn(name = "system_access_id")
    private SystemAccess systemAccess;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private Date creationDate;
    private Date lastModifiedDate;
    @ManyToOne
    @JoinColumn(name = "last_modifier_Id")
    private Requester lastModifier;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(Requester requesterID) {
        this.requester = requesterID;
    }

    public SystemAccess getSystemAccess() {
        return systemAccess;
    }

    public void setSystemAccess(SystemAccess systemAccess) {
        this.systemAccess = systemAccess;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project projectID) {
        this.project = projectID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Requester getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Requester lastModifier) {
        this.lastModifier = lastModifier;
    }
}