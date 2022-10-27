package com.infenion.ccmamodel.model;

public enum Role {
    MEMBER("MEMBER"), MANAGER("MANAGER");
    private String name;
    Role(String name) {
        this.setName( name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
