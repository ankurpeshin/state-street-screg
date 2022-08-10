package com.statestreet.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.statestreet.service.StudentServiceImpl;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.statestreet.model.Course;
import com.statestreet.model.Student;
import com.statestreet.service.CourseServiceImpl;

@RestController
public class StudentCourseController {
	private final static Logger LOG = LoggerFactory.getLogger(StudentCourseController.class);

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private CourseServiceImpl courseService;


	@GetMapping("/student/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
		return ResponseEntity.of(Optional.of(
				studentService.getStudent(studentId)));
	}


	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
		return ResponseEntity.of(Optional.of(
				studentService.registerStudent(student)));
	}

	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Object> removeStudent(@PathVariable Long studentId) {
		studentService.removeStudent(studentId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/course")
	public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course) {
		return ResponseEntity.of(Optional.of(
				courseService.addCourse(course)));

	}

	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<Object> removeCourse(@PathVariable Long courseId) {
		courseService.removeCourse(courseId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/registerStudents/{courseId}")
	public String enrollStudentToCourse(@PathVariable Long courseId, @RequestBody Set<Student> students) {
		courseService.registerStudentToCourse(courseId, students);
		return "Students has been successfully Enrolled to Course :: " + courseId;
	}

	@GetMapping("/studentsByCourse/{courseName}")
	public ResponseEntity<Set<Student>> getStudentsByCourseName(@PathVariable String courseName) {
		return ResponseEntity.of(Optional.of(
				studentService.getStudentsByCourseName(courseName)));
	}

}
