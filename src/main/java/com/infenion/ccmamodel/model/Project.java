package com.infenion.ccmamodel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jiraId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String readRoleId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String writeRoleId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String adminRoleId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String spApplicationId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String spInstanceId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String spProjectId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String spReadRoleId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String spWriteId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String spAdminRoleId;

    public Project() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJiraId() {
        return jiraId;
    }

    public void setJiraId(String jiraId) {
        this.jiraId = jiraId;
    }

    public String getJiraRoleId(AccessPermission accessPermission) {
        switch (accessPermission){
            case READ: return this.readRoleId;
            case WRITE: return this.writeRoleId;
            case ADMIN: return this.adminRoleId;
            default: return null;
        }
    }
    public String getSpRoleId(AccessPermission accessPermission) {
        switch (accessPermission){
            case READ: return this.spReadRoleId;
            case WRITE: return this.spWriteId;
            case ADMIN: return this.spAdminRoleId;
            default: return null;
        }
    }

    public String getSpApplicationId() {
        return spApplicationId;
    }

    public void setSpApplicationId(String spApplicationId) {
        this.spApplicationId = spApplicationId;
    }

    public String getSpInstanceId() {
        return spInstanceId;
    }

    public void setSpInstanceId(String spInstanceId) {
        this.spInstanceId = spInstanceId;
    }

    public String getSpProjectId() {
        return spProjectId;
    }

    public void setSpProjectId(String spProjectId) {
        this.spProjectId = spProjectId;
    }
}
