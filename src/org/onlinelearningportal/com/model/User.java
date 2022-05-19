package org.onlinelearningportal.com.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class User {

    private @Id
    @GeneratedValue
    Long id;
    UserType type;
    String year;
    List<Course> coursesRegistered;


    User() {}

    User(UserType type, String year)
    {
        this.type = type;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Course> getCoursesRegistered() {
        return coursesRegistered;
    }

    public void setCoursesRegistered(List<Course> coursesRegistered) {
        this.coursesRegistered = coursesRegistered;
    }

    public void addCourse(Course course)
    {
        if (this.coursesRegistered == null)
            this.coursesRegistered = new ArrayList<>();
        this.coursesRegistered.add(course);
    }
}
