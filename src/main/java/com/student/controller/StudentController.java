package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Course;
import com.student.model.Student;
import com.student.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students/{studentId}/courses")
	public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
		return studentService.retrieveCourses(studentId);
	}
        
        @GetMapping("/students/")
	public List<Student> retrieveStudents() {
		return studentService.retrieveStudents();
	}
        
        @GetMapping("/students/{studentId}")
	public Student retrieveStudent(@PathVariable String studentId) {
		return studentService.retrieveStudent(studentId);
	}
}
