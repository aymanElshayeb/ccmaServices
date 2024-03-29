package com.infenion.ccmamodel.model;

public enum Status {
    DRAFT("DRAFT"), PENDING("PENDING"),COMPLETED("COMPLETED");
    private String name;
    Status(String name) {
        this.setName( name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
