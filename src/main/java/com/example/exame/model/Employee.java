package com.example.exame.model;

import jakarta.persistence.*;

import java.util.*;

@Entity


public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false,length = 150)
    private String name;

    @Column(name = "email",nullable = false,length = 250)

    private String email;
    @Column(name = "skills",nullable = false,length = 250)

    private String skills;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "empoyees_projects",
            joinColumns = {@JoinColumn(name = "emloyee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")},
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = {"emloyee_id","project_id"}
                    )
            }
    )
    private Set<Project> projects = new HashSet<>();

    public Employee(int id, String name, String email, String skills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.skills = skills;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
