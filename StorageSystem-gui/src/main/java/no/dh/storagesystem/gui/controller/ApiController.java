package no.dh.storagesystem.gui.controller;

import java.util.Collection;

import no.dh.storagesystem.model.Course;
import no.dh.storagesystem.model.Degree;
import no.dh.storagesystem.model.Student;
import no.dh.storagesystem.service.StudentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	private StudentSystem studentSystem;
	
	@RequestMapping(value="student", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Student> getAllStudents(){
		return studentSystem.getAllStudents();
	}
	
	@RequestMapping(value="student/{student}/location", method = RequestMethod.GET)	 
	@ResponseBody
	public Collection<Student> setStudentLocation(@PathVariable int student, 
			@RequestParam(required=false, value="latitude") String latitude, 
			@RequestParam(required=false, value="longitude") String longitude){
		studentSystem.setStudentLocation(student, latitude, longitude);
		return studentSystem.getAllStudents();
	}
	
	@RequestMapping(value="course", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Course> getAllCourses(){
		return studentSystem.getAllCourses();
	}
	
	@RequestMapping(value="degree", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Degree> getAllDegrees(){
		return studentSystem.getAllDegrees();
	}

}
