package com.infenion.ccmamodel.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemAccess {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id ;
    private  String systemID;
    private String accessID;

    public SystemAccess(String systemID, String accessID) {
        this.systemID = systemID;
        this.accessID = accessID;
    }

    public SystemAccess() {}

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getAccessID() {
        return accessID;
    }

    public void setAccessID(String accessID) {
        this.accessID = accessID;
    }
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        return gson.toJson(this);
    }
}

