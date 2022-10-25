package com.infenion.ccmamodel.model;

import javax.persistence.*;

@Entity
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "subProjectID")
    private Project subProject;

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

    public Project getSubProject() {
        return subProject;
    }

    public void setSubProject(Project subProject) {
        this.subProject = subProject;
    }
}
