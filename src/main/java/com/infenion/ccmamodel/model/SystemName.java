package com.infenion.ccmamodel.model;

public enum SystemName {
    JIRA("JIRA"), SVN("SVN");
    private String name;
    SystemName(String name) {
        this.setName( name);
    }

    public static SystemName getEnum(String systemName) {
        if(JIRA.getName().equalsIgnoreCase(systemName)){
            return  JIRA;
        } else if (SVN.getName().equalsIgnoreCase(systemName)) {
            return SVN;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
