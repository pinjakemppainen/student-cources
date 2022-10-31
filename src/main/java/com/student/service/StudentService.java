package com.student.service;

import com.student.model.Course;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

//import com.student.model.Course;
import com.student.model.Student;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final List<Student> students = new ArrayList<>();

    static {
        // Initialize Data
//        Course course1 = new Course("Kurssi1", "Java", "Javan perusteet");
//        Course course2 = new Course("Kurssi2", "Web ohjelmointi", "www jatkokurssi");
//
//        Student pinja = new Student("id1", "Pinja Kemppainen",
//                "Opiskelija", new ArrayList<>(
//                Arrays.asList(course1, course2)));
//
//        students.add(pinja);
        
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("students.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray studentList = (JSONArray) obj;
            studentList.forEach( cs -> parseStudentObject( (JSONObject) cs ) );
        } 
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
    
    private static void parseStudentObject(JSONObject jstudent) 
    {
        JSONObject studentObject = (JSONObject) jstudent.get("student");
         
        Student student = new Student();
        student.setId((String)studentObject.get("id"));
        student.setName((String)studentObject.get("name"));
        student.setDescription((String)studentObject.get("description"));
        
        List<Course> studentCourses = new ArrayList();
        JSONArray courseList = (JSONArray) studentObject.get("courses");
        courseList.forEach( cs -> studentCourses.add(parseCourseObject( (JSONObject) cs )) );
        
        student.setCourses(studentCourses);
        
        students.add(student);
    }
    
    private static Course parseCourseObject(JSONObject jcourse) 
    {
        JSONObject courseObject = (JSONObject) jcourse.get("course");
         
        Course course = new Course();
        course.setId((String)courseObject.get("id"));
        course.setName((String)courseObject.get("name"));
        course.setDescription((String)courseObject.get("description"));
        
        return course;
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
        
        JSONArray studentList = new JSONArray();
        
        for(Student s : students)
        {
            JSONObject studentDetails = new JSONObject();
            studentDetails.put("id", s.getId());
            studentDetails.put("name", s.getName());
            studentDetails.put("description", s.getDescription());

            if(s.getCourses() != null && s.getCourses().size() > 0)
            { 
                JSONArray courseList = new JSONArray();
                for(Course c : s.getCourses())
                {
                    JSONObject courseDetails = new JSONObject();
                    courseDetails.put("id", c.getId());
                    courseDetails.put("name", c.getName());
                    courseDetails.put("description", c.getDescription());

                    JSONObject courseObject = new JSONObject(); 
                    courseObject.put("course", courseDetails); 
                    courseList.add(courseObject);
                }

                studentDetails.put("courses", courseList);
            }
            
            JSONObject studentObject = new JSONObject(); 
            studentObject.put("student", studentDetails); 
            
            studentList.add(studentObject);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter("students.json")) 
        {
            file.write(studentList.toJSONString()); 
            file.flush(); 

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
      
    public List<Course> retrieveCourses(String studentId)
    {
        for(Student s : students)
        {
            if(s.getId().equals(studentId))
            {
                return s.getCourses();
            }
        }

        return null;
    }
}