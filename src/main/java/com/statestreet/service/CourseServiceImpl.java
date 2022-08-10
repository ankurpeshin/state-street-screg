package com.statestreet.service;

import java.util.Optional;
import java.util.Set;

import com.statestreet.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statestreet.dao.repository.CourseRepository;
import com.statestreet.model.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	private final static Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);

	private CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Course addCourse(Course course) {
		course = courseRepository.save(course);
		return course;
	}

	@Override
	public void removeCourse(Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);
		if (!course.isPresent()) {
			throw new RuntimeException("Invalid CourseId :: " + courseId);
		}
		courseRepository.delete(course.get());
	}

	@Override
	public void registerStudentToCourse(Long courseId, Set<Student> students) {
		LOG.info("CourseId :: {} , Student :: {}", courseId, students);
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (!courseOptional.isPresent()) {
			throw new RuntimeException("Invalid CourseId :: " + courseId);
		}
		Course course = courseOptional.get();
		students.addAll(course.getStudents());
		course.setStudents(students);
		courseRepository.save(course);
	}

	@Override
	public Optional<Course> getCourseByCourseName(String courseName) {
		return courseRepository.findCourseByCourseName(courseName);
	}

}
