package no.dh.storagesystem.dao.hibernate;

import static org.junit.Assert.*;

import no.dh.storagesystem.dao.ProductDAO;
import no.dh.storagesystem.model.Product;

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
public class ProductDAOTest {

	@Autowired( required = true )
	private ProductDAO productDAO;
	
	private Product testProduct;
	private int id;
	
	@Before
	public void init(){
		testProduct = new Product("envelope", "C2");
		testProduct.setBoxes(10);
		testProduct.setPallets(2);
		testProduct.setQuantity(1000);
		testProduct.setShelfSpace("D8");
		testProduct.setNotice("Red");;
		id = productDAO.saveProduct(testProduct);
	}
	
	
	@Test
	public void testSaveProduct(){
		assertEquals(id, testProduct.getId());
		assertEquals("envelope", testProduct.getName());
	}
	
	@Test
	public void testGetProduct(){
		assertNotNull(productDAO.getProduct(testProduct.getId()));
	}
	
	@Test
	public void testGetProductByName(){		
		assertNotNull(productDAO.getProductByName("envelope"));
	}
	
	@Test
	public void testGetProductByShelfSpace(){		
		assertNotNull(productDAO.getProductByShelfSpace("D8"));
	}
	
	@Test
	public void testGetProductsByType(){
		assertNotNull(productDAO.getProductsByType("C2"));
	}
			
	@Test
	public void testGetAllOrders(){
		Product test1 = new Product("t1", "C1");
		Product test2 = new Product("t2", "C2");
		Product test3 = new Product("t3", "C3");
		productDAO.saveProduct(test1);
		productDAO.saveProduct(test2);
		productDAO.saveProduct(test3);
		
		assertTrue(productDAO.getAllProducts().contains(test1));
		assertTrue(productDAO.getAllProducts().contains(test2));
		assertTrue(productDAO.getAllProducts().contains(test3));
	}
	
	@Test
	public void testDelCustomer(){
		Product delTest = new Product("T4", "C4");
		productDAO.saveProduct(delTest);
		productDAO.delProduct(delTest);
		assertNull(productDAO.getProduct(delTest.getId()));
	}
}
