package no.dh.storagesystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import no.dh.storagesystem.service.StudentSystem;
import no.dh.storagesystem.model.Course;
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
@ContextConfiguration(locations = { "classpath*:META-INF/assignment2/beans.xml" })
@Transactional
public class StudentSystemTest {

	@Autowired
	StudentSystem studentSystem;

	@Before
	public void init() {

	}

	@Test
	public void testAddCourse() {
		int id = studentSystem.addCourse("INF0002", "TWO");
		Course testCourse = studentSystem.getCourse(id);
		assertEquals(testCourse.getCourseCode(), "INF0002");
		assertEquals(testCourse.getName(), "TWO");
	}

	@Test
	public void testUpdateCourse() {
		int id = studentSystem.addCourse("INF0000", "Unknown");
		studentSystem.updateCourse(id, "INF0001", "Known");
		Course testCourse = studentSystem.getCourse(id);
		assertEquals(testCourse.getCourseCode(), "INF0001");
		assertEquals(testCourse.getName(), "Known");
	}

	@Test
	public void testGetCourse() {
		int id = studentSystem.addCourse("INFXXX0", "X0");
		Course testCourse = studentSystem.getCourse(id);
		assertEquals(testCourse.getCourseCode(), "INFXXX0");
		assertEquals(testCourse.getName(), "X0");
	}

	@Test
	public void testGetCourseByCourseCode() {
		studentSystem.addCourse("INFXXX1", "X1");
		Course testCourse = studentSystem.getCourseByCourseCode("INFXXX1");
		assertEquals(testCourse.getName(), "X1");
		assertEquals(testCourse.getCourseCode(), "INFXXX1");
	}

	@Test
	public void testGetCourseByName() {
		studentSystem.addCourse("INFXXX2", "X2");
		Course testCourse = studentSystem.getCourseByName("X2");
		assertEquals(testCourse.getName(), "X2");
		assertEquals(testCourse.getCourseCode(), "INFXXX2");
	}

	@Test
	public void testGetAllCourses() {
		int id = studentSystem.addCourse("INFXXX3", "X3");
		int id1 = studentSystem.addCourse("INFXXX4", "X4");
		int id2 = studentSystem.addCourse("INFXXX5", "X5");
		Course testCourse = studentSystem.getCourse(id);
		Course testCourse1 = studentSystem.getCourse(id1);
		Course testCourse2 = studentSystem.getCourse(id2);
		Collection<Course> courses = studentSystem.getAllCourses();
		assertTrue(courses.contains(testCourse));
		assertTrue(courses.contains(testCourse1));
		assertTrue(courses.contains(testCourse2));
	}

	@Test
	public void testDelCourse() {
		int id = studentSystem.addCourse("INFXXX6", "X6");
		studentSystem.delCourse(id);
		assertNull(studentSystem.getCourse(id));
	}

	@Test
	public void testAddAttendantToCourse() {
		int courseId = studentSystem.addCourse("INF0003", "THREE");
		int studentId = studentSystem.addStudent("John");
		studentSystem.addAttendantToCourse(courseId, studentId);
		Course testCourse = studentSystem.getCourse(courseId);
		Student testStudent = studentSystem.getStudent(studentId);

		assertTrue(testCourse.getAttendants().contains(testStudent));
		assertTrue(testStudent.getCourses().contains(testCourse));
	}

	@Test
	public void testRemoveAttendantFromCourse() {
		int courseId = studentSystem.addCourse("INF0004", "FOUR");
		int studentId = studentSystem.addStudent("Bob");
		studentSystem.addAttendantToCourse(courseId, studentId);
		Course testCourse = studentSystem.getCourse(courseId);
		Student testStudent = studentSystem.getStudent(studentId);
		studentSystem.removeAttendantFromCourse(courseId, studentId);

		assertFalse(testCourse.getAttendants().contains(testStudent));
		assertFalse(testStudent.getCourses().contains(testCourse));
	}

	@Test
	public void testAddDegree() {
		int id = studentSystem.addDegree("Tester1");
		Degree testDegree = studentSystem.getDegree(id);
		assertEquals(testDegree.getType(), "Tester1");
	}

	@Test
	public void testUpdateDegree() {
		int id = studentSystem.addDegree("Tester2");
		studentSystem.updateDegree(id, "Tester2.1");
		Degree testDegree = studentSystem.getDegree(id);
		assertEquals(testDegree.getType(), "Tester2.1");
	}

	@Test
	public void testGetDegree() {
		int id = studentSystem.addDegree("D0");
		Degree degree = studentSystem.getDegree(id);
		assertEquals(degree.getType(), "D0");
	}

	@Test
	public void testGetDegreeByType() {
		studentSystem.addDegree("D1");
		Degree degree = studentSystem.getDegreeByType("D1");
		assertEquals(degree.getType(), "D1");
	}

	@Test
	public void testGetAllDegrees() {
		int id = studentSystem.addDegree("D2");
		int id1 = studentSystem.addDegree("D3");
		int id2 = studentSystem.addDegree("D4");
		Degree degree = studentSystem.getDegree(id);
		Degree degree1 = studentSystem.getDegree(id1);
		Degree degree2 = studentSystem.getDegree(id2);

		Collection<Degree> degrees = studentSystem.getAllDegrees();
		assertTrue(degrees.contains(degree));
		assertTrue(degrees.contains(degree1));
		assertTrue(degrees.contains(degree2));
	}

	@Test
	public void testDelDegree() {
		int id = studentSystem.addDegree("D5");
		studentSystem.delDegree(id);
		assertNull(studentSystem.getDegree(id));
	}

	@Test
	public void testAddRequiredCourseToDegree() {
		int courseId = studentSystem.addCourse("INF0005", "FIVE");
		int degreeId = studentSystem.addDegree("Tester3");
		studentSystem.addRequiredCourseToDegree(degreeId, courseId);
		Course testCourse = studentSystem.getCourse(courseId);
		Degree testDegree = studentSystem.getDegree(degreeId);

		assertTrue(testDegree.getRequiredCourses().contains(testCourse));
	}

	@Test
	public void testRemoveRequiredCourseFromDegree() {
		int courseId = studentSystem.addCourse("INF0006", "SIX");
		int degreeId = studentSystem.addDegree("Tester4");
		studentSystem.addRequiredCourseToDegree(degreeId, courseId);
		Course testCourse = studentSystem.getCourse(courseId);
		Degree testDegree = studentSystem.getDegree(degreeId);
		studentSystem.removeRequiredCourseFromDegree(degreeId, courseId);

		assertFalse(testDegree.getRequiredCourses().contains(testCourse));
	}

	@Test
	public void testAddStudent() {
		int id = studentSystem.addStudent("Jane");
		Student testStudent = studentSystem.getStudent(id);
		assertEquals(testStudent.getName(), "Jane");
	}

	@Test
	public void testUpdateStudent() {
		int id = studentSystem.addStudent("Jane1");
		studentSystem.updateStudent(id, "Jane Doe");
		Student testStudent = studentSystem.getStudent(id);
		assertEquals(testStudent.getName(), "Jane Doe");
	}

	@Test
	public void testGetStudent() {
		int id = studentSystem.addStudent("S0");
		Student student = studentSystem.getStudent(id);
		assertEquals(student.getName(), "S0");
	}

	@Test
	public void testGetStudentByName() {
		int id = studentSystem.addStudent("S1");
		Student student = studentSystem.getStudentByName("S1");
		assertEquals(student.getName(), "S1");
		assertEquals(student.getId(), id);
	}
	
	@Test
	public void testSetStudentLocation(){
		int id = studentSystem.addStudent("Tracker");
		studentSystem.setStudentLocation(id, "22", "33");
		Student student = studentSystem.getStudent(id);
		assertEquals(student.getLatitude(), "22");
		assertEquals(student.getLongitude(), "33");
	}

	@Test
	public void testGetAllStudents() {
		int id = studentSystem.addStudent("S2");
		int id1 = studentSystem.addStudent("S3");
		int id2 = studentSystem.addStudent("S4");
		Student student = studentSystem.getStudent(id);
		Student student1 = studentSystem.getStudent(id1);
		Student student2 = studentSystem.getStudent(id2);

		Collection<Student> students = studentSystem.getAllStudents();
		assertTrue(students.contains(student));
		assertTrue(students.contains(student1));
		assertTrue(students.contains(student2));
	}

	@Test
	public void testDelStudent() {
		int id = studentSystem.addStudent("S5");
		studentSystem.delStudent(id);
		assertNull(studentSystem.getStudent(id));
	}

	@Test
	public void testAddDegreeToStudent() {
		int studentId = studentSystem.addStudent("Mike");
		int degreeId = studentSystem.addDegree("Tester5");
		studentSystem.addDegreeToStudent(studentId, degreeId);
		Student testStudent = studentSystem.getStudent(studentId);
		Degree testDegree = studentSystem.getDegree(degreeId);

		assertTrue(testStudent.getDegrees().contains(testDegree));
	}

	@Test
	public void testRemoveDegreeFromStudent() {
		int studentId = studentSystem.addStudent("Amanda");
		int degreeId = studentSystem.addDegree("Tester6");
		studentSystem.addDegreeToStudent(studentId, degreeId);
		Student testStudent = studentSystem.getStudent(studentId);
		Degree testDegree = studentSystem.getDegree(degreeId);
		studentSystem.removeDegreeFromStudent(studentId, degreeId);

		assertFalse(testStudent.getDegrees().contains(testDegree));
	}

	@Test
	public void testStudentFulfillsDegreeRequirements() {
		int degreeId = studentSystem.addDegree("Tester7");
		int studentId = studentSystem.addStudent("Batman");
		int studentId2 = studentSystem.addStudent("Robin");
		int courseId = studentSystem.addCourse("INF0007", "SEVEN");
		int courseId2 = studentSystem.addCourse("INF0008", "EIGHT");
		studentSystem.addRequiredCourseToDegree(degreeId, courseId);
		studentSystem.addRequiredCourseToDegree(degreeId, courseId2);
		studentSystem.addAttendantToCourse(courseId, studentId);
		studentSystem.addAttendantToCourse(courseId2, studentId);
		studentSystem.addAttendantToCourse(courseId2, studentId2);

		assertTrue(studentSystem.studentFulfillsDegreeRequirements(studentId,
				degreeId));
		assertFalse(studentSystem.studentFulfillsDegreeRequirements(studentId2,
				degreeId));
	}

}
