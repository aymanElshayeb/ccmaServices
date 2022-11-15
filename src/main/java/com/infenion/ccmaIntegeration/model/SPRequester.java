package com.infenion.ccmaIntegeration.model;

import com.infenion.ccmamodel.model.BaseEntity;

import javax.persistence.*;

@Entity
public class SPRequester extends BaseEntity {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
