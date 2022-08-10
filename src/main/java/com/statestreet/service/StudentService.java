package com.statestreet.service;

import com.statestreet.model.Course;
import com.statestreet.model.Student;

import java.util.Set;

public interface StudentService {
    Student registerStudent(Student student);
    Student getStudent(Long studentId);
    void removeStudent(Long studentId);
    void registerCourse(Long studentId, Set<Course> courses);
    Set<Student> getStudentsByCourseName(String courseName);
}
