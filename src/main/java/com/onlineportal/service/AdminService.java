package com.onlineportal.service;

import com.onlineportal.model.Admin;
import com.onlineportal.model.Course;
import com.onlineportal.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    Admin admin = new Admin();

    public List<User> getUsers()
    {
        if (admin.getUsers() == null)
            return new ArrayList<>();
        return admin.getUsers();
    }

    public List<Course> getCourses()
    {
        if (admin.getCourses() == null)
            return new ArrayList<>();
        return admin.getCourses();
    }

    public void addCourse(Course course)
    {
        Course addCourse = new Course();
        addCourse.setId(course.getId());
        addCourse.setName(course.getName());
        addCourse.setYear(course.getYear());
        addCourse.setHasPrerequites(course.getHasPrerequites());
        addCourse.setPrerequites(course.getPrerequites());

        if (admin.getCourses() == null)
            return;
        admin.getCourses().add(addCourse);
    }

    public void addUser(User user)
    {
        if (admin.getUsers() == null)
            return;
        admin.getUsers().add(user);
    }

    public User getUserById(Long id)
    {
        if (admin.getUsers() == null || admin.getUsers().size() == 0) return null;

        for (User user: admin.getUsers())
        {
            if (user.getId() == id) return user;
        }

        return null;
    }

    public Course getCourseById(Long id)
    {
        if (admin.getCourses() == null || admin.getCourses().size() == 0) return null;

        for (Course course: admin.getCourses())
        {
            if (course.getId() == id) return course;
        }

        return null;
    }
}
