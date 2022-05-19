package org.onlinelearningportal.com.service;

import org.onlinelearningportal.com.model.Admin;
import org.onlinelearningportal.com.model.Course;
import org.onlinelearningportal.com.model.User;
import org.onlinelearningportal.com.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class portalService {

    @Autowired
    User user;

    @Autowired
    ClassRegistrationService classRegistrationService;

    @Autowired
    Course course;

    @Autowired
    Admin admin;


    @PostMapping("/online-portal/add/course")
    public void addCourse(@RequestBody Course course)
    {
        if (user.getCoursesRegistered() != null && user.getCoursesRegistered().size() > 0)
        {
            if (user.getCoursesRegistered().contains(course))
            {
                System.out.println("This course is already registered");
            }
            else
            {
                boolean completedPrere = true;
                if (course.isHasPrerequites())
                {
                    List<Course> prere = course.getPrerequites();
                    for (Course pre : prere)
                    {
                        if (!user.getCoursesRegistered().contains(pre))
                        {
                            System.out.println("Please register course " +pre.getName() + " with course number "+pre.getId());
                            completedPrere = false;
                        }
                    }
                }

                if (completedPrere)
                {
                    user.addCourse(course);
                }
            }
        }
    }

    @GetMapping("/online-portal/courses")
    public List<Course> getAllCourses()
    {
        if (admin.getCourses() == null || admin.getCourses().size() == 0) return new ArrayList<>();
        return admin.getCourses();
    }

    @GetMapping("/online-portal/students")
    public List<User> getAllStudents()
    {
        if (admin.getUsers() == null || admin.getUsers().size() == 0) return new ArrayList<>();

        List<User> students = new ArrayList<>();

        for (User user: admin.getUsers())
        {
            if (user.getType() == UserType.STUDENT)
                students.add(user);
        }

        return students;
    }

    @GetMapping("/online-portal/admins")
    public List<User> getAllAdmins()
    {
        if (admin.getUsers() == null || admin.getUsers().size() == 0) return new ArrayList<>();

        List<User> students = new ArrayList<>();

        for (User user: admin.getUsers())
        {
            if (user.getType() == UserType.ADMIN)
                students.add(user);
        }

        return students;
    }

    @PostMapping("/online-portal/add/user")
    public void addUser(@RequestBody User user)
    {
        admin.addUser(user);
        return;
    }

    @PostMapping("/online-portal/user/{id}/add/course/{courseId}")
    public void addCourseToUser(@PathVariable Long courseId, @PathVariable Long id)
    {
        if (admin.getUsers() == null || admin.getUsers().size() == 0)
        {
            System.out.println("We do not have any user with id "+id);
            return;
        }
        if (admin.getCourses() == null || admin.getCourses().size() == 0)
        {
            System.out.println("Currently we have 0 courses, so we are going to add this new course");
            return;
        }

        User currUser = admin.getUserById(id);
        Course currCourse = admin.getCourseById(courseId);

        currUser.addCourse(currCourse);

        return;
    }

}
