package com.statestreet.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.statestreet.dao.repository.StudentRepository;
import com.statestreet.model.Course;
import com.statestreet.model.Student;

public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepositoryMock;
	
	@Mock
	private CourseServiceImpl courseServiceMock;

	@InjectMocks
	private StudentServiceImpl studentService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddCourse() {
		Student student = new Student();
		student.setStudentId(1l);
		Mockito.when(studentRepositoryMock.save(Mockito.any(Student.class))).thenReturn(student);
		Student savedStudent = studentService.registerStudent(student);
		Assert.assertEquals(1, savedStudent.getStudentId().longValue());
	}

	@Test
	public void testRemoveCourse() {
		Long courseId = 1l;
		Student student = new Student();
		student.setStudentId(1l);
		Mockito.when(studentRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(student));
		studentService.removeStudent(courseId);
	}

	@Test(expected = RuntimeException.class)
	public void testRemoveCourseWithEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(studentRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		studentService.removeStudent(courseId);
	}

	@Test
	public void testRegisterStudentToCourse() {
		Long studentId = 1l;
		Student source = new Student();
		source.setStudentId(studentId);
		source.setCourses(Collections.emptySet());
		Mockito.when(studentRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(source));
		Set<Course> courses = Collections.emptySet();
		studentService.registerCourse(studentId, courses);

	}

	@Test(expected = RuntimeException.class)
	public void testRegisterStudentToCoursEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(studentRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Set<Course> courses = Collections.emptySet();
		studentService.registerCourse(courseId, courses);

	}
	
	@Test
	public void testGetStudentsByCourseName() {
		String courseName = "DevOps";
		Course course = new Course();
		course.setCourseId(1l);
		course.setCourseName(courseName);
		
		Set<Student> studentsSet = new HashSet<>();
		
		Student student = new Student();
		student.setStudentName("NA");
		studentsSet.add(student);
		course.setStudents(studentsSet);
		course.setCourseName("NA");
		Mockito.when(courseServiceMock.getCourseByCourseName(Mockito.anyString())).thenReturn(Optional.of(course));
		Set<Student> students = studentService.getStudentsByCourseName(courseName);
		Assert.assertNotNull(students);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetStudentsByCourseNameWithEmptyCourse() {
		String courseName = "DevOps";
		Mockito.when(courseServiceMock.getCourseByCourseName(Mockito.anyString())).thenReturn(Optional.empty());
		Set<Student> students = studentService.getStudentsByCourseName(courseName);
		Assert.assertNotNull(students);
	}

}
