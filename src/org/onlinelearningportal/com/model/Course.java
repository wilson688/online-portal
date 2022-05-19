package org.onlinelearningportal.com.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class Course {

    private @Id
    @GeneratedValue
    Long id;
    String name;
    String year;
    boolean hasPrerequites;
    List<Course> prerequites;

    public Long getId() {
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

    public boolean isHasPrerequites() {
        return hasPrerequites;
    }

    public void setHasPrerequites(boolean hasPrerequites) {
        this.hasPrerequites = hasPrerequites;
    }

    public List<Course> getPrerequites() {
        return prerequites;
    }

    public void setPrerequites(List<Course> prerequites) {
        this.prerequites = prerequites;
    }
}
