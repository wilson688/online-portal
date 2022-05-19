package com.onlineportal.model;


import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

public class Course {

    private long id;
    private String name;
    private String year;
    private boolean hasPrerequites;


    public Course() { }

    @Nullable
    List<Course> prerequites;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean getHasPrerequites() {
        return hasPrerequites;
    }

    public void setHasPrerequites(boolean hasPrerequites) {
        this.hasPrerequites = hasPrerequites;
    }

    @Nullable
    public List<Course> getPrerequites() {
        return prerequites;
    }

    public void setPrerequites(@Nullable List<Course> prerequites) {
        this.prerequites = prerequites;
    }
}
