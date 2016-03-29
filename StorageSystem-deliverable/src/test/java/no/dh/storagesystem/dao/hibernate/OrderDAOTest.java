package no.dh.storagesystem.dao.hibernate;

import static org.junit.Assert.*;

import java.util.Date;

import no.dh.storagesystem.dao.CustomerDAO;
import no.dh.storagesystem.dao.OrderDAO;
import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Order;

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
public class OrderDAOTest {

	@Autowired( required = true )
	private OrderDAO orderDAO;
	
	@Autowired( required = true )
	private CustomerDAO customerDAO;
	
	private Order testOrder;
	private Customer testCustomer;
	private Date date;
	private int id;
	
	@Before
	public void init(){
		testOrder = new Order("1234");
		testCustomer = new Customer("Arne");
		customerDAO.saveCustomer(testCustomer);
		testOrder.setCustomer(testCustomer);
		date = new Date();
	//	testOrder.setDate(date);
		id = orderDAO.saveOrder(testOrder);
	}
	
	
	@Test
	public void testSaveOrder(){
		assertEquals(id, testOrder.getId());
		assertEquals("1234", testOrder.getOrderNo());
	}
	
	@Test
	public void testGetOrder(){
		assertNotNull(orderDAO.getOrder(testOrder.getId()));
	}
	
	@Test
	public void testGetOrderByOrderNo(){		
		assertNotNull(orderDAO.getOrderByOrderNo("1234"));
	}
	
	@Test
	public void testGetOrdersByCustomer(){
		assertNotNull(orderDAO.getOrdersByCustomer(testCustomer));
	}
	
	@Test
	public void testGetOrdersByDate(){
		assertNotNull(orderDAO.getOrdersByDate(date));
	}
		
	@Test
	public void testGetAllOrders(){
		Order test1 = new Order("1111");
		Order test2 = new Order("2222");
		Order test3 = new Order("3333");
		orderDAO.saveOrder(test1);
		orderDAO.saveOrder(test2);
		orderDAO.saveOrder(test3);
		
		assertTrue(orderDAO.getAllOrders().contains(test1));
		assertTrue(orderDAO.getAllOrders().contains(test2));
		assertTrue(orderDAO.getAllOrders().contains(test3));
	}
	
	@Test
	public void testDelCustomer(){
		Order delTest = new Order("0000");
		orderDAO.saveOrder(delTest);
		orderDAO.delOrder(delTest);
		assertNull(orderDAO.getOrder(delTest.getId()));
	}
}
