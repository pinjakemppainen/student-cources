package com.student.service;

import java.util.ArrayList;
import java.util.List;

import com.student.model.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private static final List<Course> courses = new ArrayList<>();

    static {
        // Initialize Data
        Course course1 = new Course("Kurssi1", "Java", "Javan perusteet");
        Course course2 = new Course("Kurssi2", "Web ohjelmointi", "www jatkokurssi");

        courses.add(course1);
        courses.add(course2);
    }

    public Course retrieveCourse(String courseId) {
            for (Course course : courses) {
                    if (course.getId().equals(courseId)) {
                            return course;
                    }
            }
            return null;
    }

    public List<Course> retrieveCourses() {
            return courses;
    }

    public void addCourse(Course course) {

        System.out.println("Luotu kurssi: " + course.getName());
        courses.add(course);

    }
}

