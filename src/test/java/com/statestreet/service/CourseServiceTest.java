package com.statestreet.service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.statestreet.dao.repository.CourseRepository;
import com.statestreet.model.Course;
import com.statestreet.model.Student;

public class CourseServiceTest {

	@Mock
	private CourseRepository courseRepositoryMock;

	@InjectMocks
	private CourseServiceImpl courseService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddCourse() {
		Course course = new Course();
		course.setCourseId(2l);
		Mockito.when(courseRepositoryMock.save(Mockito.any(Course.class))).thenReturn(course);
		Course savedCourse = courseService.addCourse(course);
		Assert.assertEquals(2, course.getCourseId().longValue());
	}

	@Test
	public void testRemoveCourse() {
		Long courseId = 1l;
		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("Spring");
		Mockito.when(courseRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
		courseService.removeCourse(courseId);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRemoveCourseWithEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(courseRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		courseService.removeCourse(courseId);
	}

	@Test
	public void testRegisterStudentToCourse() {
		Long courseId = 1l;
		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("Spring");
		course.setStudents(Collections.emptySet());
		Mockito.when(courseRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
		Set<Student> students = Collections.emptySet();
		courseService.registerStudentToCourse(courseId, students);

	}
	
	@Test(expected=RuntimeException.class)
	public void testRegisterStudentToCoursEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(courseRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Set<Student> students = Collections.emptySet();
		courseService.registerStudentToCourse(courseId, students);

	}

	@Test
	public void testGetCourseByCourseName() {
		String courseName = "DevOps";
		courseService.getCourseByCourseName(courseName);
	}

}
