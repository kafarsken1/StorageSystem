package no.dh.storagesystem.service.impl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import no.dh.storagesystem.service.StorageSystem;
import no.dh.storagesystem.dao.CourseDAO;
import no.dh.storagesystem.dao.DegreeDAO;
import no.dh.storagesystem.dao.StudentDAO;
import no.dh.storagesystem.model.Course;
import no.dh.storagesystem.model.Degree;
import no.dh.storagesystem.model.Student;

public class DefaultStudentSystem implements StorageSystem {
	
	static Logger logger = Logger.getLogger(DefaultStudentSystem.class);
	
    private CourseDAO courseDAO;
	private DegreeDAO degreeDAO;
	private StudentDAO studentDAO;
	
	public void setCourseDAO(CourseDAO courseDAO){
		this.courseDAO = courseDAO;
	}
	
	public void setDegreeDAO(DegreeDAO degreeDAO){
		this.degreeDAO = degreeDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO){
		this.studentDAO = studentDAO;
	}

	public int addCourse(String courseCode, String name) {
		Course course = new Course(courseCode, name);
		return courseDAO.saveCourse(course);
	}

	public void updateCourse(int courseId, String courseCode, String name) {
		Course course = courseDAO.getCourse(courseId);
		course.setCourseCode(courseCode);
		course.setName(name);
		courseDAO.saveCourse(course);		
	}

	public Course getCourse(int courseId) {
		return courseDAO.getCourse(courseId);
	}

	public Course getCourseByCourseCode(String courseCode) {
		return courseDAO.getCourseByCourseCode(courseCode);
	}

	public Course getCourseByName(String name) {
		return courseDAO.getCourseByName(name);
	}

	public Collection<Course> getAllCourses(){ 
		return courseDAO.getAllCourses();
	}

	public void delCourse(int courseId) {
		Course course = courseDAO.getCourse(courseId);
		
		Iterator<Degree> i = degreeDAO.getAllDegrees().iterator();
		while(i.hasNext()){
			Degree d = (Degree) i.next();
			if(d.getRequiredCourses().contains(course)){
				d.getRequiredCourses().remove(course);
				degreeDAO.saveDegree(d);
			}
		}
		courseDAO.delCourse(course);
	}

	public void addAttendantToCourse(int courseId, int studentId) {
		Course course = courseDAO.getCourse(courseId);
		Student student = studentDAO.getStudent(studentId);
		course.getAttendants().add(student);
		student.getCourses().add(course);
		System.out.println(student.getCourses().toArray().length);
		studentDAO.saveStudent(student);
	}

	public void removeAttendantFromCourse(int courseId, int studentId) {
		Course course = courseDAO.getCourse(courseId);
		Student student = studentDAO.getStudent(studentId);
		student.getCourses().remove(course);
		course.getAttendants().remove(student);
		studentDAO.saveStudent(student);
	}

	public int addDegree(String type) {
		Degree degree = new Degree(type);
		return degreeDAO.saveDegree(degree);
	}

	public void updateDegree(int degreeId, String type) {
		Degree degree = degreeDAO.getDegree(degreeId);
		degree.setType(type);
		degreeDAO.saveDegree(degree);
	}

	public Degree getDegree(int degreeId) {
		return degreeDAO.getDegree(degreeId);
	}

	public Degree getDegreeByType(String type) {
		return degreeDAO.getDegreeByType(type);
	}

	public Collection<Degree> getAllDegrees() {
		return degreeDAO.getAllDegrees();
	}

	public void delDegree(int degreeId) {
		degreeDAO.delDegree(degreeDAO.getDegree(degreeId));
	}

	public void addRequiredCourseToDegree(int degreeId, int courseId) {
		Degree degree = degreeDAO.getDegree(degreeId);
		Course course = courseDAO.getCourse(courseId);
		degree.getRequiredCourses().add(course);
		degreeDAO.saveDegree(degree);
	}

	public void removeRequiredCourseFromDegree(int degreeId, int courseId) {
		degreeDAO.getDegree(degreeId).getRequiredCourses().remove(courseDAO.getCourse(courseId));
	}

	public int addStudent(String name) {
		Student student = new Student(name);
		return studentDAO.saveStudent(student);
	}

	public void updateStudent(int studentId, String name) {
		Student student = studentDAO.getStudent(studentId);
		student.setName(name);
		studentDAO.saveStudent(student);
	}

	public Student getStudent(int studentId) {
		return studentDAO.getStudent(studentId);
	}

	public Student getStudentByName(String name) {
		return studentDAO.getStudentByName(name);
	}
	
	public void setStudentLocation(int studentId, String latitude,
			String longitude) {
		Student student = studentDAO.getStudent(studentId);
		student.setLatitude(latitude);
		student.setLongitude(longitude);
		studentDAO.saveStudent(student);
	}

	public Collection<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}

	public void delStudent(int studentId) {
		Student student = studentDAO.getStudent(studentId);
		Iterator<Course> i = student.getCourses().iterator();
		while(i.hasNext()){
			Course c = (Course) i.next();
			c.getAttendants().remove(student);
			courseDAO.saveCourse(c);
		}
		studentDAO.delStudent(student);
	}

	public void addDegreeToStudent(int studentId, int degreeId) {
		Student student = studentDAO.getStudent(studentId);
		student.getDegrees().add(degreeDAO.getDegree(degreeId));
		studentDAO.saveStudent(student);
	}

	public void removeDegreeFromStudent(int studentId, int degreeId) {
		studentDAO.getStudent(studentId).getDegrees().remove(degreeDAO.getDegree(degreeId));
	}

	public boolean studentFulfillsDegreeRequirements(int studentId, int degreeId) {
		Student student = studentDAO.getStudent(studentId);
		Degree degree = degreeDAO.getDegree(degreeId);
		return student.getCourses().containsAll(degree.getRequiredCourses());
	}

}
