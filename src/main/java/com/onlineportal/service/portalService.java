package com.onlineportal.service;

import com.onlineportal.model.Admin;
import com.onlineportal.model.Course;
import com.onlineportal.model.User;
import com.onlineportal.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class portalService {

    User user = new User();

    ClassRegistrationService classRegistrationService;

    Course course;

    @Autowired
    AdminService adminService;


    @PostMapping("/online-portal/add/course")
    public void addCourse(@RequestBody Course course)
    {
        if (adminService.getCourses() != null && adminService.getCourses().size() > 0)
        {
            if (adminService.getCourses().contains(course))
            {
                System.out.println("This course is already added");
                return;
            }
        }

        if (course.getHasPrerequites())
        {
            List<Course> prere = course.getPrerequites();
            for (Course pre : prere)
            {
                if (!adminService.getCourses().contains(pre))
                {
                    //add all prerequites
                    adminService.addCourse(pre);
                }
            }
        }

        adminService.addCourse(course);
    }

    @GetMapping("/online-portal/courses")
    public List<Course> getAllCourses()
    {
        if (adminService.getCourses() == null || adminService.getCourses().size() == 0) return new ArrayList<>();
        return adminService.getCourses();
    }

    @GetMapping("/online-portal/students")
    public List<User> getAllStudents()
    {
        if (adminService.getUsers() == null || adminService.getUsers().size() == 0) return new ArrayList<>();

        List<User> students = new ArrayList<>();

        for (User user: adminService.getUsers())
        {
            if (user.getType() == UserType.STUDENT)
                students.add(user);
        }

        return students;
    }

    @GetMapping("/online-portal/admins")
    public List<User> getAllAdmins()
    {
        if (adminService.getUsers() == null || adminService.getUsers().size() == 0) return new ArrayList<>();

        List<User> students = new ArrayList<>();

        for (User user: adminService.getUsers())
        {
            if (user.getType() == UserType.ADMIN)
                students.add(user);
        }

        return students;
    }

    @PostMapping("/online-portal/add/user")
    public void addUser(@RequestBody User user)
    {
        adminService.addUser(user);
        return;
    }

    @PostMapping("/online-portal/user/{id}/add/course/{courseId}")
    public void addCourseToUser(@PathVariable long courseId, @PathVariable long id)
    {
        if (adminService.getUsers() == null || adminService.getUsers().size() == 0)
        {
            System.out.println("We do not have any user with id "+id);
            return;
        }
        if (adminService.getCourses() == null || adminService.getCourses().size() == 0)
        {
            System.out.println("Currently we have 0 courses, so we are going to add this new course");
            return;
        }

        User currUser = adminService.getUserById(id);
        Course currCourse = adminService.getCourseById(courseId);

        if (currCourse.getHasPrerequites())
        {
            List<Course> requirements = currCourse.getPrerequites();
            for (Course reg: requirements)
            {
                if (currUser.getCoursesRegistered() != null && currUser.getCoursesRegistered().size() > 0 && !currUser.getCoursesRegistered().contains(reg))
                {
                    currUser.addCourse(reg);
                }
            }
        }

        currUser.addCourse(currCourse);

        return;
    }

}
