package no.dh.storagesystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import no.dh.storagesystem.service.StorageSystem;
import no.dh.storagesystem.model.Course;
import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Degree;
import no.dh.storagesystem.model.Student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/storagesystem/beans.xml" })
@Transactional
public class StorageSystemTest {

	@Autowired
	StorageSystem storageSystem;

	@Before
	public void init() {

	}

	@Test
	public void testAddCustomer() {
		int id = storageSystem.addCustomer("User");
		Customer testCustomer = storageSystem.getCustomer(id);
		assertEquals(testCustomer.getName(), "User");
	}

	@Test
	public void testUpdateCustomer() {
		int id = storageSystem.addCustomer("User1");
		storageSystem.updateCustomer(id, "User2", "12345678", "Street 1");
		Customer testCustomer = storageSystem.getCustomer(id);
		assertEquals(testCustomer.getName(), "User2");
		assertEquals(testCustomer.getPhone(), "12345678");
		assertEquals(testCustomer.getAddress(), "Street 1");
	}

	@Test
	public void testGetCourse() {
		int id = storageSystem.addCustomer("User3");
		Customer testCustomer = storageSystem.getCustomer(id);
		assertEquals(testCustomer.getName(), "User3");
	}

	@Test
	public void testGetCustomerByName() {
		storageSystem.addCustomer("User4");
		Customer testCustomer = storageSystem.getCustomerByName("User4");
		assertEquals(testCustomer.getName(), "User4");
	}

	@Test
	public void testGetCustomerByPhone() {
		int id = storageSystem.addCustomer("User5");
		storageSystem.updateCustomer(id, "User5", "12345678", "Street 2");
		Customer testCustomer = storageSystem.getCustomerByPhone("12345678");
		assertEquals(testCustomer.getName(), "User5");
		assertEquals(testCustomer.getPhone(), "12345678");
	}

	@Test
	public void testGetAllCustomers() {
		int id = storageSystem.addCustomer("User6");
		int id1 = storageSystem.addCustomer("User7");
		int id2 = storageSystem.addCustomer("User8");
		Customer testCustomer = storageSystem.getCustomer(id);
		Customer testCustomer1 = storageSystem.getCustomer(id1);
		Customer testCustomer2 = storageSystem.getCustomer(id2);
		Collection<Customer> customers = storageSystem.getAllCustomers();
		assertTrue(customers.contains(testCustomer));
		assertTrue(customers.contains(testCustomer1));
		assertTrue(customers.contains(testCustomer2));
	}

	@Test
	public void testDelCourse() {
		int id = storageSystem.addCourse("INFXXX6", "X6");
		storageSystem.delCourse(id);
		assertNull(storageSystem.getCourse(id));
	}

	@Test
	public void testAddAttendantToCourse() {
		int courseId = storageSystem.addCourse("INF0003", "THREE");
		int studentId = storageSystem.addStudent("John");
		storageSystem.addAttendantToCourse(courseId, studentId);
		Course testCourse = storageSystem.getCourse(courseId);
		Student testStudent = storageSystem.getStudent(studentId);

		assertTrue(testCourse.getAttendants().contains(testStudent));
		assertTrue(testStudent.getCourses().contains(testCourse));
	}

	@Test
	public void testRemoveAttendantFromCourse() {
		int courseId = storageSystem.addCourse("INF0004", "FOUR");
		int studentId = storageSystem.addStudent("Bob");
		storageSystem.addAttendantToCourse(courseId, studentId);
		Course testCourse = storageSystem.getCourse(courseId);
		Student testStudent = storageSystem.getStudent(studentId);
		storageSystem.removeAttendantFromCourse(courseId, studentId);

		assertFalse(testCourse.getAttendants().contains(testStudent));
		assertFalse(testStudent.getCourses().contains(testCourse));
	}

	@Test
	public void testAddDegree() {
		int id = storageSystem.addDegree("Tester1");
		Degree testDegree = storageSystem.getDegree(id);
		assertEquals(testDegree.getType(), "Tester1");
	}

	@Test
	public void testUpdateDegree() {
		int id = storageSystem.addDegree("Tester2");
		storageSystem.updateDegree(id, "Tester2.1");
		Degree testDegree = storageSystem.getDegree(id);
		assertEquals(testDegree.getType(), "Tester2.1");
	}

	@Test
	public void testGetDegree() {
		int id = storageSystem.addDegree("D0");
		Degree degree = storageSystem.getDegree(id);
		assertEquals(degree.getType(), "D0");
	}

	@Test
	public void testGetDegreeByType() {
		storageSystem.addDegree("D1");
		Degree degree = storageSystem.getDegreeByType("D1");
		assertEquals(degree.getType(), "D1");
	}

	@Test
	public void testGetAllDegrees() {
		int id = storageSystem.addDegree("D2");
		int id1 = storageSystem.addDegree("D3");
		int id2 = storageSystem.addDegree("D4");
		Degree degree = storageSystem.getDegree(id);
		Degree degree1 = storageSystem.getDegree(id1);
		Degree degree2 = storageSystem.getDegree(id2);

		Collection<Degree> degrees = storageSystem.getAllDegrees();
		assertTrue(degrees.contains(degree));
		assertTrue(degrees.contains(degree1));
		assertTrue(degrees.contains(degree2));
	}

	@Test
	public void testDelDegree() {
		int id = storageSystem.addDegree("D5");
		storageSystem.delDegree(id);
		assertNull(storageSystem.getDegree(id));
	}

	@Test
	public void testAddRequiredCourseToDegree() {
		int courseId = storageSystem.addCourse("INF0005", "FIVE");
		int degreeId = storageSystem.addDegree("Tester3");
		storageSystem.addRequiredCourseToDegree(degreeId, courseId);
		Course testCourse = storageSystem.getCourse(courseId);
		Degree testDegree = storageSystem.getDegree(degreeId);

		assertTrue(testDegree.getRequiredCourses().contains(testCourse));
	}

	@Test
	public void testRemoveRequiredCourseFromDegree() {
		int courseId = storageSystem.addCourse("INF0006", "SIX");
		int degreeId = storageSystem.addDegree("Tester4");
		storageSystem.addRequiredCourseToDegree(degreeId, courseId);
		Course testCourse = storageSystem.getCourse(courseId);
		Degree testDegree = storageSystem.getDegree(degreeId);
		storageSystem.removeRequiredCourseFromDegree(degreeId, courseId);

		assertFalse(testDegree.getRequiredCourses().contains(testCourse));
	}

	@Test
	public void testAddStudent() {
		int id = storageSystem.addStudent("Jane");
		Student testStudent = storageSystem.getStudent(id);
		assertEquals(testStudent.getName(), "Jane");
	}

	@Test
	public void testUpdateStudent() {
		int id = storageSystem.addStudent("Jane1");
		storageSystem.updateStudent(id, "Jane Doe");
		Student testStudent = storageSystem.getStudent(id);
		assertEquals(testStudent.getName(), "Jane Doe");
	}

	@Test
	public void testGetStudent() {
		int id = storageSystem.addStudent("S0");
		Student student = storageSystem.getStudent(id);
		assertEquals(student.getName(), "S0");
	}

	@Test
	public void testGetStudentByName() {
		int id = storageSystem.addStudent("S1");
		Student student = storageSystem.getStudentByName("S1");
		assertEquals(student.getName(), "S1");
		assertEquals(student.getId(), id);
	}
	
	@Test
	public void testSetStudentLocation(){
		int id = storageSystem.addStudent("Tracker");
		storageSystem.setStudentLocation(id, "22", "33");
		Student student = storageSystem.getStudent(id);
		assertEquals(student.getLatitude(), "22");
		assertEquals(student.getLongitude(), "33");
	}

	@Test
	public void testGetAllStudents() {
		int id = storageSystem.addStudent("S2");
		int id1 = storageSystem.addStudent("S3");
		int id2 = storageSystem.addStudent("S4");
		Student student = storageSystem.getStudent(id);
		Student student1 = storageSystem.getStudent(id1);
		Student student2 = storageSystem.getStudent(id2);

		Collection<Student> students = storageSystem.getAllStudents();
		assertTrue(students.contains(student));
		assertTrue(students.contains(student1));
		assertTrue(students.contains(student2));
	}

	@Test
	public void testDelStudent() {
		int id = storageSystem.addStudent("S5");
		storageSystem.delStudent(id);
		assertNull(storageSystem.getStudent(id));
	}

	@Test
	public void testAddDegreeToStudent() {
		int studentId = storageSystem.addStudent("Mike");
		int degreeId = storageSystem.addDegree("Tester5");
		storageSystem.addDegreeToStudent(studentId, degreeId);
		Student testStudent = storageSystem.getStudent(studentId);
		Degree testDegree = storageSystem.getDegree(degreeId);

		assertTrue(testStudent.getDegrees().contains(testDegree));
	}

	@Test
	public void testRemoveDegreeFromStudent() {
		int studentId = storageSystem.addStudent("Amanda");
		int degreeId = storageSystem.addDegree("Tester6");
		storageSystem.addDegreeToStudent(studentId, degreeId);
		Student testStudent = storageSystem.getStudent(studentId);
		Degree testDegree = storageSystem.getDegree(degreeId);
		storageSystem.removeDegreeFromStudent(studentId, degreeId);

		assertFalse(testStudent.getDegrees().contains(testDegree));
	}

	@Test
	public void testStudentFulfillsDegreeRequirements() {
		int degreeId = storageSystem.addDegree("Tester7");
		int studentId = storageSystem.addStudent("Batman");
		int studentId2 = storageSystem.addStudent("Robin");
		int courseId = storageSystem.addCourse("INF0007", "SEVEN");
		int courseId2 = storageSystem.addCourse("INF0008", "EIGHT");
		storageSystem.addRequiredCourseToDegree(degreeId, courseId);
		storageSystem.addRequiredCourseToDegree(degreeId, courseId2);
		storageSystem.addAttendantToCourse(courseId, studentId);
		storageSystem.addAttendantToCourse(courseId2, studentId);
		storageSystem.addAttendantToCourse(courseId2, studentId2);

		assertTrue(storageSystem.studentFulfillsDegreeRequirements(studentId,
				degreeId));
		assertFalse(storageSystem.studentFulfillsDegreeRequirements(studentId2,
				degreeId));
	}

}
