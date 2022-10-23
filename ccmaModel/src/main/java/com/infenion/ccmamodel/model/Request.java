package com.infenion.ccmamodel.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.*;



@Entity
public class Request extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String requesterID;
    @ManyToOne
    @JoinColumn(name = "systemaccessID")
    private SystemAccess systemAccess;
    private Status status;
    private String projectID;
    private String subprojectID;

    public Request(String requesterID, SystemAccess systemAccess, String statusID, String projectID, String subProjectID) {
        this.requesterID = requesterID;
        this.systemAccess = systemAccess;
        this.status = status;
        this.projectID = projectID;
        this.subprojectID = subProjectID;
    }

    public Request() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequesterID() {
        return requesterID;
    }

    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
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

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getSubprojectID() {
        return subprojectID;
    }

    public void setSubprojectID(String subprojectID) {
        this.subprojectID = subprojectID;
    }


}