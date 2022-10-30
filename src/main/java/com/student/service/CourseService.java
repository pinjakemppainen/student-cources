package com.student.service;

import java.util.ArrayList;
import java.util.List;

import com.student.model.Course;
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
public class CourseService {

    private static final List<Course> courses = new ArrayList<>();

    static 
    {
        // Initialize Data
        //Course course1 = new Course("Kurssi1", "Java", "Javan perusteet");
        //Course course2 = new Course("Kurssi2", "Web ohjelmointi", "www jatkokurssi");

        //courses.add(course1);
        //courses.add(course2);
        
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("courses.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray courseList = (JSONArray) obj;
            courseList.forEach( cs -> parseCourseObject( (JSONObject) cs ) );
        } 
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
    
    private static void parseCourseObject(JSONObject jcourse) 
    {
        JSONObject courseObject = (JSONObject) jcourse.get("course");
         
        Course course = new Course();
        course.setId((String)courseObject.get("id"));
        course.setName((String)courseObject.get("name"));
        course.setDescription((String)courseObject.get("description"));
        courses.add(course);
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
        
        JSONArray courseList = new JSONArray();
        
        for(Course c : courses)
        {
            JSONObject courseDetails = new JSONObject();
            courseDetails.put("id", c.getId());
            courseDetails.put("name", c.getName());
            courseDetails.put("description", c.getDescription());

            JSONObject courseObject = new JSONObject(); 
            courseObject.put("course", courseDetails); 
            courseList.add(courseObject);
        }
        
        //Write JSON file
        try (FileWriter file = new FileWriter("courses.json")) 
        {
            file.write(courseList.toJSONString()); 
            file.flush(); 

        } catch (IOException e) 
        {
            e.printStackTrace();
        }



    }
}

