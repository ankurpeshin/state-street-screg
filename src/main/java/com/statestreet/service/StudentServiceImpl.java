package com.statestreet.service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statestreet.dao.repository.StudentRepository;
import com.statestreet.model.Course;
import com.statestreet.model.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	private final static Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

	private StudentRepository studentRepository;
	private CourseServiceImpl courseService;

	@Autowired
	public StudentServiceImpl(CourseServiceImpl courseService, StudentRepository studentRepository) {
		this.courseService = courseService;
		this.studentRepository = studentRepository;
	}

	@Override
	public Student registerStudent(Student student) {
		student = studentRepository.save(student);
		LOG.info("Student {} Successfully added", student.getStudentId());
		return student;
	}

	@Override
	public Student getStudent(Long studentId) {
		Optional<Student> student = studentRepository.findStudentByStudentId(studentId);
		return student.orElse(null);
	}

	@Override
	public void removeStudent(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if (!student.isPresent()) {
			throw new RuntimeException("Invalid StudentId :: " + studentId);
		}
		studentRepository.delete(student.get());
	}

	@Override
	public void registerCourse(Long studentId, Set<Course> courses) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (!studentOptional.isPresent()) {
			throw new RuntimeException("Invalid CourseId :: " + studentId);
		}
		Student student = studentOptional.get();
		courses.addAll(student.getCourses());
		student.setCourses(courses);
		studentRepository.save(student);
	}

	@Override
	public Set<Student> getStudentsByCourseName(String courseName) {
		Optional<Course> course = courseService.getCourseByCourseName(courseName);
		if (!course.isPresent()) {
			throw new RuntimeException("Invalid courseName :: " + courseName);
		}
		Set<Student> students = course.get().getStudents();
		return students;
	}

}
