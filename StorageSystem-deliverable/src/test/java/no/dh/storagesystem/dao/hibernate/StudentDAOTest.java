package no.dh.storagesystem.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import no.dh.storagesystem.dao.StudentDAO;
import no.dh.storagesystem.model.Student;

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
public class StudentDAOTest {
		
	@Autowired
	private StudentDAO studentDAO;
	
	private Student testStudent;
	private int id;
	
	@Before
	public void init(){
		testStudent = new Student("John");
		id = studentDAO.saveStudent(testStudent);
	}
	
	
	@Test
	public void testSaveStudent(){
		assertEquals(id, testStudent.getId());
		assertEquals("John", testStudent.getName());
	}
	
	@Test
	public void testGetStudent(){
		assertNotNull(studentDAO.getStudent(testStudent.getId()));
	}
	
	@Test
	public void testGetStudentByName(){
		assertNotNull(studentDAO.getStudentByName(testStudent.getName()));
	}
	
	@Test
	public void testGetAllStudents(){
		Student student1 = new Student("Student1");
		Student student2 = new Student("Student2");
		Student student3 = new Student("Student3");
		studentDAO.saveStudent(student1);
		studentDAO.saveStudent(student2);
		studentDAO.saveStudent(student3);
		
		assertTrue(studentDAO.getAllStudents().contains(student1));
		assertTrue(studentDAO.getAllStudents().contains(student2));
		assertTrue(studentDAO.getAllStudents().contains(student3));
	}
	
	@Test
	public void testDelStudent(){
		Student delStudent = new Student("Quitter");
		studentDAO.saveStudent(delStudent);
		studentDAO.delStudent(delStudent);
		assertNull(studentDAO.getStudent(delStudent.getId()));
	}

}
