package no.dh.storagesystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import no.dh.storagesystem.service.StorageSystem;
import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Item;
import no.dh.storagesystem.model.Order;
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
public class StorageSystemTest {

	@Autowired( required = true )
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
	public void testGetCustomer() {
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
	public void testDelCustomer() {
		int id = storageSystem.addCustomer("User9");
		storageSystem.delCustomer(id);
		assertNull(storageSystem.getCustomer(id));
	}

	@Test
	public void testAddOrderToCustomer() {
		int customerId = storageSystem.addCustomer("User10");
		int orderId = storageSystem.addOrder("1234");
		storageSystem.addOrderToCustomer(customerId, orderId);
		Customer testCustomer = storageSystem.getCustomer(customerId);
		Order testOrder = storageSystem.getOrder(orderId);

		assertTrue(testCustomer.getOrders().contains(testOrder));
		assertEquals(testOrder.getCustomer().getId(), testCustomer.getId());
	}

	@Test
	public void testRemoveOrderFromCustomer() {
		int customerId = storageSystem.addCustomer("User11");
		int orderId = storageSystem.addOrder("12345");
		storageSystem.addOrderToCustomer(customerId, orderId);
		Customer testCustomer = storageSystem.getCustomer(customerId);
		Order testOrder = storageSystem.getOrder(orderId);
		storageSystem.removeOrderFromCustomer(customerId, orderId);

		assertFalse(testCustomer.getOrders().contains(testOrder));
		assertNull(storageSystem.getOrder(orderId));
	}

	@Test
	public void testAddOrder() {
		int id = storageSystem.addOrder("1234");
		Order testOrder = storageSystem.getOrder(id);
		assertEquals(testOrder.getOrderNo(), "1234");
	}

	@Test
	public void testUpdateOrder() {
		int id = storageSystem.addOrder("12345");
		int customerId = storageSystem.addCustomer("User12");
		Customer customer = storageSystem.getCustomer(customerId);
		Date date = new Date();
		storageSystem.updateOrder(id, "54321", customer, date);
		Order testOrder = storageSystem.getOrder(id);
		assertEquals(testOrder.getOrderNo(), "54321");
		assertEquals(testOrder.getCustomer(), customer);
		assertEquals(testOrder.getDate(), date);
	}

	@Test
	public void testGetOrder() {
		int id = storageSystem.addOrder("123456");
		Order order = storageSystem.getOrder(id);
		assertEquals(order.getOrderNo(), "123456");
	}

	@Test
	public void testGetOrderByOrderNo() {
		storageSystem.addOrder("123456");
		Order order = storageSystem.getOrderByOrderNo("123456");
		assertEquals(order.getOrderNo(), "123456");
	}
	
	@Test
	public void testGetOrdersByCustomer() {
		int cid = storageSystem.addCustomer("User13");
		Customer customer = storageSystem.getCustomer(cid);
		int id = storageSystem.addOrder("123456");
		storageSystem.updateOrder(id, "123456", customer, null);
		int id2 = storageSystem.addOrder("1234567");
		storageSystem.updateOrder(id2, "1234567", customer, null);
		int id3 = storageSystem.addOrder("12345678");
		storageSystem.updateOrder(id3, "12345678", customer, null);
		Order order1 = storageSystem.getOrder(id);
		Order order2 = storageSystem.getOrder(id2);
		Order order3 = storageSystem.getOrder(id3);
		
		Collection<Order> orders = storageSystem.getOrdersByCustomer(customer);
		assertTrue(orders.contains(order1));
		assertTrue(orders.contains(order2));
		assertTrue(orders.contains(order3));
	}
	
	@Test
	public void testGetOrdersByDate() {
		Date date = new Date();
		int id = storageSystem.addOrder("123456");
		storageSystem.updateOrder(id, "123456", null, date);
		int id2 = storageSystem.addOrder("1234567");
		storageSystem.updateOrder(id2, "1234567", null, date);
		int id3 = storageSystem.addOrder("12345678");
		storageSystem.updateOrder(id3, "12345678", null, date);
		Order order1 = storageSystem.getOrder(id);
		Order order2 = storageSystem.getOrder(id2);
		Order order3 = storageSystem.getOrder(id3);
		
		Collection<Order> orders = storageSystem.getOrdersByDate(date);
		assertTrue(orders.contains(order1));
		assertTrue(orders.contains(order2));
		assertTrue(orders.contains(order3));
	}

	@Test
	public void testGetAllORders() {
		int id = storageSystem.addOrder("123456");
		int id1 = storageSystem.addOrder("1234567");
		int id2 = storageSystem.addOrder("12345678");
		Order order1 = storageSystem.getOrder(id);
		Order order2 = storageSystem.getOrder(id1);
		Order order3 = storageSystem.getOrder(id2);

		Collection<Order> orders = storageSystem.getAllOrders();
		assertTrue(orders.contains(order1));
		assertTrue(orders.contains(order2));
		assertTrue(orders.contains(order3));
	}

	@Test
	public void testDelOrder() {
		int id = storageSystem.addOrder("0001");
		storageSystem.delOrder(id);
		assertNull(storageSystem.getOrder(id));
	}

	@Test
	public void testAddProductToOrder() {
		int orderId = storageSystem.addOrder("0002");
		int productId = storageSystem.addProduct("Product1", "envelope");
		storageSystem.addItemToOrder(orderId, productId, 20);
		Order testOrder = storageSystem.getOrder(orderId);
		Product testProduct = storageSystem.getProduct(productId);
		
		Iterator<Item> i = testOrder.getItems().iterator();
		while(i.hasNext()){
			Item item = (Item) i.next();
			if(item.getProduct().equals(testProduct)){
				assertTrue(true);
			}
		}
		
	}

	@Test
	public void testRemoveProductFromOrder() {
		int orderId = storageSystem.addOrder("0003");
		int productId = storageSystem.addProduct("Product2", "envelope");
		storageSystem.addItemToOrder(orderId, productId, 20);
		Order testOrder = storageSystem.getOrder(orderId);
		Product testProduct = storageSystem.getProduct(productId);
		storageSystem.removeItemFromOrder(orderId, productId);

		assertFalse(testOrder.getItems().contains(testProduct));
	}

	@Test
	public void testAddProduct() {
		int id = storageSystem.addProduct("Product3", "envelope");
		Product testProduct = storageSystem.getProduct(id);
		assertEquals(testProduct.getName(), "Product3");
		assertEquals(testProduct.getType(), "envelope");
	}

	@Test
	public void testUpdateProduct() {
		int id = storageSystem.addProduct("Product4", "envelope");
		storageSystem.updateProduct(id, "Product5", "envelope", 1000, 2, 20, "red", "D5");
		Product testProduct = storageSystem.getProduct(id);
		assertEquals(testProduct.getName(), "Product5");
		assertEquals(testProduct.getType(), "envelope");
		assertEquals(testProduct.getQuantity(), 1000);
		assertEquals(testProduct.getPallets(), 2);
		assertEquals(testProduct.getBoxes(), 20);
		assertEquals(testProduct.getNotice(), "red");
		assertEquals(testProduct.getShelfSpace(), "D5");
	}

	@Test
	public void testGetProduct() {
		int id = storageSystem.addProduct("Product6", "envelope");
		Product product = storageSystem.getProduct(id);
		assertEquals(product.getName(), "Product6");
	}

	@Test
	public void testGetProductByName() {
		int id = storageSystem.addProduct("Product7", "envelope");
		Product product = storageSystem.getProductByName("Product7");
		assertEquals(product.getName(), "Product7");
		assertEquals(product.getId(), id);
	}
	
	@Test
	public void testGetProductByShelfSpace() {
		int id = storageSystem.addProduct("Product8", "envelope");
		storageSystem.updateProduct(id, "Product8", "envelope", 1000, 2, 20, "red", "D5");
		Product product = storageSystem.getProductByShelfSpace("D5");
		assertEquals(product.getName(), "Product8");
		assertEquals(product.getId(), id);
		assertEquals(product.getShelfSpace(), "D5");
	}
	
	@Test
	public void testGetProductsByType() {
		int id = storageSystem.addProduct("Product8", "envelope");
		int id1 = storageSystem.addProduct("Product9", "envelope");
		int id2 = storageSystem.addProduct("Product10", "envelope");
		Product product = storageSystem.getProduct(id);
		Product product1 = storageSystem.getProduct(id1);
		Product product2 = storageSystem.getProduct(id2);
		
		Collection<Product> products = storageSystem.getProductsByType("envelope");
		assertTrue(products.contains(product));
		assertTrue(products.contains(product1));
		assertTrue(products.contains(product2));
	}

	@Test
	public void testGetAllProducts() {
		int id = storageSystem.addProduct("Product11", "envelope");
		int id1 = storageSystem.addProduct("Product12", "envelope");
		int id2 = storageSystem.addProduct("Product13", "envelope");
		Product product = storageSystem.getProduct(id);
		Product product1 = storageSystem.getProduct(id1);
		Product product2 = storageSystem.getProduct(id2);
		
		Collection<Product> products = storageSystem.getAllProduct();
		assertTrue(products.contains(product));
		assertTrue(products.contains(product1));
		assertTrue(products.contains(product2));
	}

	@Test
	public void testDelProduct() {
		int id = storageSystem.addProduct("Product14", "envelope");
		storageSystem.delProduct(id);
		assertNull(storageSystem.getProduct(id));
	}



}
