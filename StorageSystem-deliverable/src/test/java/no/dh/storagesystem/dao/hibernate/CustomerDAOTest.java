package no.dh.storagesystem.dao.hibernate;

import static org.junit.Assert.*;

import no.dh.storagesystem.dao.CustomerDAO;
import no.dh.storagesystem.model.Customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/storagesystem/beans.xml"})
@Transactional
public class CustomerDAOTest {

	@Autowired( required = true )
	private CustomerDAO customerDAO;
	
	private Customer testCustomer;
	private int id;
	
	@Before
	public void init(){
		testCustomer = new Customer("Arne");
		testCustomer.setPhone("12345678");
		testCustomer.setAddress("Street 1");
		id = customerDAO.saveCustomer(testCustomer);
	}
	
	
	@Test
	public void testSaveCustomer(){
		assertEquals(id, testCustomer.getId());
		assertEquals("Arne", testCustomer.getName());
	}
	
	@Test
	public void testGetCustomer(){
		assertNotNull(customerDAO.getCustomer(testCustomer.getId()));
	}
	
	@Test
	public void testGetCustomerByName(){		
		assertNotNull(customerDAO.getCustomerByName("Arne"));
	}
	
	@Test
	public void testGetCustomerByPhone(){
		assertNotNull(customerDAO.getCustomerByPhone(testCustomer.getPhone()));
	}
		
	@Test
	public void testGetAllCustomers(){
		Customer test1 = new Customer("Test1");
		Customer test2 = new Customer("Test2");
		Customer test3 = new Customer("Test3");
		customerDAO.saveCustomer(test1);
		customerDAO.saveCustomer(test2);
		customerDAO.saveCustomer(test3);
		
		assertTrue(customerDAO.getAllCustomers().contains(test1));
		assertTrue(customerDAO.getAllCustomers().contains(test2));
		assertTrue(customerDAO.getAllCustomers().contains(test3));
	}
	
	@Test
	public void testDelCustomer(){
		Customer delTest = new Customer("DeleteCustomer");
		customerDAO.saveCustomer(delTest);
		customerDAO.delCustomer(delTest);
		assertNull(customerDAO.getCustomer(delTest.getId()));
	}
}
