package org.onlinelearningportal.com.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin {

    List<User> users;

    List<Course> courses;


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course)
    {
        if (this.courses == null)
            this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public void addUser(User user)
    {
        if (this.users == null)
            this.users = new ArrayList<>();
        this.users.add(user);
    }

    public User getUserById(Long id)
    {
        if (users == null || users.size() == 0) return null;

        for (User user: users)
        {
            if (user.getId() == id) return user;
        }

        return null;
    }

    public Course getCourseById(Long id)
    {
        if (courses == null || courses.size() == 0) return null;

        for (Course course: courses)
        {
            if (course.getId() == id) return course;
        }

        return null;
    }
}