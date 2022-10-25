package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Course;
import com.student.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author pinja
 */
@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

        @GetMapping("/courses")
	public List<Course> retrieveCourses() {
		return courseService.retrieveCourses();
	}
        
        @GetMapping("/courses/{courseId}")
	public Course retrieveCourse(@PathVariable String courseId) {
		return courseService.retrieveCourse(courseId);
	}
        
        @PostMapping("/addCourse")
        public String addCourse(@RequestBody Course course){
            courseService.addCourse(course);
        return "Course added";
    }
}
