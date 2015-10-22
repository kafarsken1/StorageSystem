package no.dh.storagesystem.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;

import no.dh.storagesystem.service.StorageSystem;
import no.dh.storagesystem.dao.CustomerDAO;
import no.dh.storagesystem.dao.OrderDAO;
import no.dh.storagesystem.dao.ProductDAO;
import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Order;
import no.dh.storagesystem.model.Product;

public class DefaultStorageSystem implements StorageSystem {
	
	static Logger logger = Logger.getLogger(DefaultStorageSystem.class);
	
    private CustomerDAO customerDAO;
	private OrderDAO orderDAO;
	private ProductDAO productDAO;
		
	public void setCustomerDAO(CustomerDAO customerDAO){
		this.customerDAO = customerDAO;
	}
	
	public void setOrderDAO(OrderDAO orderDAO){
		this.orderDAO = orderDAO;
	}
	public void setProductDAO(ProductDAO productDAO){
		this.productDAO = productDAO;
	}

	@Override
	public int addCustomer(String name) {
		Customer customer = new Customer(name);
		return customerDAO.saveCustomer(customer);
	}

	@Override
	public void updateCustomer(int customerId, String name, String phone, String address) {
		Customer customer = customerDAO.getCustomer(customerId);
		customer.setName(name);
		customer.setPhone(phone);
		customer.setAddress(address);
		customerDAO.saveCustomer(customer);
	}

	@Override
	public Customer getCustomer(int customerId) {
		return customerDAO.getCustomer(customerId);
	}

	@Override
	public Customer getCustomerByName(String name) {
		return customerDAO.getCustomerByName(name);
	}

	@Override
	public Customer getCustomerByPhone(String phone) {
		return customerDAO.getCustomerByPhone(phone);
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	@Override
	public void delCustomer(int customerId) {
		Customer customer = customerDAO.getCustomer(customerId);
	
		Iterator<Order> i = orderDAO.getOrdersByCustomer(customer).iterator();
		while(i.hasNext()){
			Order o = (Order) i.next();
			orderDAO.delOrder(o);
		}
		customerDAO.delCustomer(customer);
	}

	@Override
	public void addOrderToCustomer(int customerId, int orderId) {
		Customer customer = customerDAO.getCustomer(customerId);
		Order order = orderDAO.getOrder(orderId);
		customer.getOrders().add(order);
		order.setCustomer(customer);
		System.out.println(customer.getOrders().toArray().length);
		orderDAO.saveOrder(order);
		customerDAO.saveCustomer(customer);
	}

	@Override
	public void removeOrderFromCustomer(int customerId, int orderId) {
		Customer customer = customerDAO.getCustomer(customerId);
		Order order = orderDAO.getOrder(orderId);
		customer.getOrders().remove(order);
		orderDAO.delOrder(order);
		customerDAO.saveCustomer(customer);
	}

	@Override
	public int addOrder(String orderNo) {
		Order order = new Order(orderNo);
		return orderDAO.saveOrder(order);
	}

	@Override
	public void updateOrder(int orderId, String orderNo, Customer customer, Date date) {
		Order order = orderDAO.getOrder(orderId);
		order.setCustomer(customer);
		order.setDate(date);
		orderDAO.saveOrder(order);
	}

	@Override
	public Order getOrder(int orderId) {
		return orderDAO.getOrder(orderId);
	}

	@Override
	public Order getOrderByOrderNo(String orderNo) {
		return orderDAO.getOrderByOrderNo(orderNo);
	}

	@Override
	public Collection<Order> getAllOrders() {
		return orderDAO.getAllOrders();
	}

	@Override
	public Collection<Order> getOrdersByCustomer(Customer customer) {
		return orderDAO.getOrdersByCustomer(customer);
	}

	@Override
	public Collection<Order> getOrdersByDate(Date date) {
		return orderDAO.getOrdersByDate(date);
	}

	@Override
	public void delOrder(int orderId) {
		Order order = orderDAO.getOrder(orderId);
		Customer customer = customerDAO.getCustomer(order.getCustomer().getId());
		if(customer.getOrders().contains(order)){
			customer.getOrders().remove(order);
			customerDAO.saveCustomer(customer);
		}
		orderDAO.delOrder(order);
	}

	@Override
	public void addProductToOrder(int orderId, int productId) {
		Order order = orderDAO.getOrder(orderId);
		Product product = productDAO.getProduct(productId);
		order.getProducts().add(product);
		System.out.println(order.getProducts().toArray().length);
		orderDAO.saveOrder(order);
	}

	@Override
	public void removeProductFromOrder(int orderId, int productId) {
		Order order = orderDAO.getOrder(orderId);
		Product product = productDAO.getProduct(productId);
		order.getProducts().remove(product);
		orderDAO.saveOrder(order);
	}

	@Override
	public int addProduct(String name, String type) {
		Product product = new Product(name, type);
		return productDAO.saveProduct(product);
	}

	@Override
	public void updateProduct(int productId, String name, String type, int quantity, int pallets, int boxes,
			String notice, String shelfSpace) {
		Product product = productDAO.getProduct(productId);
		product.setName(name);
		product.setType(type);
		product.setQuantity(quantity);
		product.setPallets(pallets);
		product.setBoxes(boxes);
		product.setNotice(notice);
		product.setShelfSpace(shelfSpace);
		productDAO.saveProduct(product);
	}

	@Override
	public Product getProduct(int productId) {
		return productDAO.getProduct(productId);
	}

	@Override
	public Product getProductByName(String name) {
		return productDAO.getProductByName(name);
	}

	@Override
	public Product getProductByShelfSpace(String shelfSpace) {
		return productDAO.getProductByShelfSpace(shelfSpace);
	}

	@Override
	public Collection<Product> getProductsByType(String type) {
		return productDAO.getProductsByType(type);
	}

	@Override
	public Collection<Product> getAllProduct() {
		return productDAO.getAllProducts();
	}

	@Override
	public void delProduct(int productId) {
		Product product = productDAO.getProduct(productId);
		
		Iterator<Order> i = orderDAO.getAllOrders().iterator();
		while(i.hasNext()){
			Order o = (Order) i.next();
			if(o.getProducts().contains(product)){
				o.getProducts().remove(product);
				orderDAO.saveOrder(o);
			}
		}
		productDAO.delProduct(product);
	}

}
