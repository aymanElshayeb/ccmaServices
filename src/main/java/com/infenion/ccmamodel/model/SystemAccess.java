package com.infenion.ccmamodel.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;

@Entity
public class SystemAccess extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id ;
    @Enumerated(EnumType.STRING)
    private  SystemName systemName;
    @Enumerated(EnumType.STRING)
    private AccessPermission accessPermission;


    public SystemAccess() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccessPermission getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(AccessPermission accessPermission) {
        this.accessPermission = accessPermission;
    }

    public SystemName getSystemName() {
        return systemName;
    }

    public void setSystemName(SystemName systemName) {
        this.systemName = systemName;
    }
}

