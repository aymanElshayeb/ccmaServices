package com.infenion.ccmamodel.model;

public enum AccessPermission {
    READ("READ ACCESS"), WRITE("WRITE ACCESS"), ADMIN("ADMIN ACCESS");
    private String name;
    AccessPermission(String name) {
        this.setName( name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
