package com.onlineportal.service;

import com.onlineportal.model.Course;
import org.junit.Test;
import org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @Test
    public void testAddCourse() throws NullPointerException
    {
        Course newCourse = new Course();
        newCourse.setId(1L);
        newCourse.setHasPrerequites(false);
        newCourse.setYear("2020");
        newCourse.setName("Introduction to Computers");

        Course newCourse1 = new Course();
        newCourse1.setId(2L);
        newCourse1.setHasPrerequites(true);
        newCourse1.setYear("2020");
        newCourse1.setName("Java");
        List<Course> dependencies = new ArrayList<>();
        dependencies.add(newCourse);
        newCourse1.setPrerequites(dependencies);

        adminService = new AdminService();
        adminService.addCourse(newCourse);
        adminService.addCourse(newCourse1);

        List<Course> courses = adminService.getCourses();
        assertNotNull(courses);
        assertNotNull(courses.get(0).getId());
        assertNotNull(courses.get(1).getId());
        assertNotNull(courses.get(1).getName());
        assertTrue(courses.get(1).getPrerequites().size() > 0);

    }


}
