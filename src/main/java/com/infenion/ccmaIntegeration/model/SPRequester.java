package com.infenion.ccmaIntegeration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.infenion.ccmamodel.model.BaseEntity;

import javax.persistence.*;


public class SPRequester extends BaseEntity {
    @JsonProperty("Id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
