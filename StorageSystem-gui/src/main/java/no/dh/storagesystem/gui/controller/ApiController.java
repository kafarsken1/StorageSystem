package no.dh.storagesystem.gui.controller;

import java.util.Collection;

import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Order;
import no.dh.storagesystem.model.Product;
import no.dh.storagesystem.service.StorageSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	private StorageSystem storageSystem;
	
	@RequestMapping(value="customer", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Customer> getAllCustomers(){
		return storageSystem.getAllCustomers();
	}
	
/*	@RequestMapping(value="student/{student}/location", method = RequestMethod.GET)	 
	@ResponseBody
	public Collection<Student> setStudentLocation(@PathVariable int student, 
			@RequestParam(required=false, value="latitude") String latitude, 
			@RequestParam(required=false, value="longitude") String longitude){
		studentSystem.setStudentLocation(student, latitude, longitude);
		return studentSystem.getAllStudents();
	}
*/	
	@RequestMapping(value="order", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Order> getAllOrders(){
		return storageSystem.getAllOrders();
	}
	
	@RequestMapping(value="product", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Product> getAllProducts(){
		return storageSystem.getAllProduct();
	}

}
