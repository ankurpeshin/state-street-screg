package com.statestreet.service;

import com.statestreet.model.Course;
import com.statestreet.model.Student;

import java.util.Optional;
import java.util.Set;

public interface CourseService {
    Course addCourse(Course course);

    void removeCourse(Long courseId);

    void registerStudentToCourse(Long courseId, Set<Student> students);

    Optional<Course> getCourseByCourseName(String courseName);
}
