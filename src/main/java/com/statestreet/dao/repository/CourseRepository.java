package com.statestreet.dao.repository;

import java.util.Optional;

import com.statestreet.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

	public Optional<Course> findCourseByCourseName(String courseName);
}
