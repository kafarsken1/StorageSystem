package no.dh.storagesystem.dao.hibernate;

import static org.junit.Assert.*;

import no.dh.storagesystem.dao.CourseDAO;
import no.dh.storagesystem.model.Course;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/assignment2/beans.xml"})
@Transactional
public class CourseDAOTest {

	@Autowired
	private CourseDAO courseDAO;
	
	private Course testCourse;
	private int id;
	
	@Before
	public void init(){
		testCourse = new Course("INF0000", "Ukjent");
		id = courseDAO.saveCourse(testCourse);
	}
	
	
	@Test
	public void testSaveCourse(){
		assertEquals(id, testCourse.getId());
		assertEquals("Ukjent", testCourse.getName());
	}
	
	@Test
	public void testGetCourse(){
		assertNotNull(courseDAO.getCourse(testCourse.getId()));
	}
	
	@Test
	public void testGetCourseByCourseCode(){
		assertNotNull(courseDAO.getCourseByCourseCode(testCourse.getCourseCode()));
	}
	
	@Test
	public void testGetCourseByName(){		
		assertNotNull(courseDAO.getCourseByName("Ukjent"));
	}
	
	@Test
	public void testGetAllCourses(){
		Course test1 = new Course("1", "Test1");
		Course test2 = new Course("2", "Test2");
		Course test3 = new Course("3", "Test3");
		courseDAO.saveCourse(test1);
		courseDAO.saveCourse(test2);
		courseDAO.saveCourse(test3);
		
		assertTrue(courseDAO.getAllCourses().contains(test1));
		assertTrue(courseDAO.getAllCourses().contains(test2));
		assertTrue(courseDAO.getAllCourses().contains(test3));
	}
	
	@Test
	public void testDelCourse(){
		Course delTest = new Course("DEL0001", "DeleteCourse");
		courseDAO.saveCourse(delTest);
		courseDAO.delCourse(delTest);
		assertNull(courseDAO.getCourse(delTest.getId()));
	}
}
