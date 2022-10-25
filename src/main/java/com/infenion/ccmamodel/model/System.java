package com.infenion.ccmamodel.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class System {

    private long id;
    private String systemName;

    public System() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

}