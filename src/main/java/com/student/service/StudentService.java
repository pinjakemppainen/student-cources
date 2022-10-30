package com.student.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.student.model.Course;
import com.student.model.Student;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final List<Student> students = new ArrayList<>();

    static {
        // Initialize Data
        Course course1 = new Course("Kurssi1", "Java", "Javan perusteet");
        Course course2 = new Course("Kurssi2", "Web ohjelmointi", "www jatkokurssi");

        Student pinja = new Student("id1", "Pinja Kemppainen",
                "Opiskelija", new ArrayList<>(
                Arrays.asList(course1, course2)));

        students.add(pinja);
    }

	public List<Course> retrieveCourses(String studentId) {
		Student student = retrieveStudent(studentId);

		return student == null ? null : student.getCourses();
	}

	public Student retrieveStudent(String studentId) {
		for (Student student : students) {
			if (student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}
        
        public List<Student> retrieveStudents() {
		return students;
	}
        
        public void addStudent(Student student) {
            
            System.out.println("Luotu oppilas: " + student.getName());
            students.add(student);
            
            

	}
}