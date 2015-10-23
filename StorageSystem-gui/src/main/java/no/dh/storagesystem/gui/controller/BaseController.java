package no.dh.storagesystem.gui.controller;

import java.util.Collection;
import org.apache.log4j.Logger;

import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Order;
import no.dh.storagesystem.model.Product;
import no.dh.storagesystem.service.StorageSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Thomas Iversen
 */

@Controller
public class BaseController {

	static Logger logger = Logger.getLogger(BaseController.class);

	@Autowired
	private StorageSystem storageSystem;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Leave a message using the form");

		populateModel(model);
		return "index";

	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(ModelMap model) {

		if (storageSystem == null) {
			model.addAttribute("message",
					"Error filling in data. storageSystem == null");
		} else {
			int john = storageSystem.addCustomer("John McClane");
			int jane = storageSystem.addCustomer("Jane Fonda");
			int o0001 = storageSystem.addOrder("0001");
			int o0002 = storageSystem.addOrder("0002");

			int p1 = storageSystem.addProduct("Product 1", "envelope");
			int p2 = storageSystem.addProduct("Product 2", "envelope");
			int p3 = storageSystem.addProduct("Product 3", "envelope");
			
			storageSystem.addProductToOrder(o0001, p1);
			storageSystem.addProductToOrder(o0001, p2);
			storageSystem.addProductToOrder(o0002, p3);
			storageSystem.addOrderToCustomer(john, o0001);
			storageSystem.addOrderToCustomer(jane, o0002);

			model.addAttribute("message", "Filled in data OK");
		}

		model.addAttribute("message", "Filling in data");

		populateModel(model);
		return "index";

	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String listCustomers(ModelMap model) {
		populateModel(model);
		return "customer";
	}

	@RequestMapping(value = "/customer/new", method = RequestMethod.POST)
	public String createStudent(ModelMap model,
			@RequestParam("name") String name) {

		storageSystem.addCustomer(name);
		populateModel(model);
		return "customer";
	}

	@RequestMapping(value = "/customer/{customerId}/delete", method = RequestMethod.GET)
	public String deleteCustomer(ModelMap model,
			@PathVariable("customerId") int customerId) {

		storageSystem.delCustomer(customerId);
		populateModel(model);
		return "customer";
	}

	@RequestMapping(value = "/customer/{customerId}/order", method = RequestMethod.POST)
	public String addCustomerToOrder(ModelMap model,
			@PathVariable("customerId") int customerId,
			@RequestParam("orderid") int orderId) {

		logger.debug("Adding an order to a customer " + customerId + " in order "
				+ orderId);
		storageSystem.addOrderToCustomer(customerId, orderId);
		populateModel(model);
		return "customer";
	}

	@RequestMapping(value = "/customer/{customerId}/removeorder/{orderId}", method = RequestMethod.GET)
	public String removeOrderFromCustomer(ModelMap model,
			@PathVariable("customerId") int customerId,
			@PathVariable("orderId") int orderId) {

		logger.debug("Removing customer " + customerId + " from order "
				+ orderId);
		storageSystem.removeOrderFromCustomer(customerId, orderId);
		populateModel(model);
		return "customer";
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String listOrders(ModelMap model) {

		populateModel(model);
		return "order";
	}

	@RequestMapping(value = "/order/new", method = RequestMethod.POST)
	public String createOrder(ModelMap model,
			@RequestParam("orderNo") String orderNo) {

		storageSystem.addOrder(orderNo);
		populateModel(model);
		return "order";
	}

	@RequestMapping(value = "/order/{orderId}/delete", method = RequestMethod.GET)
	public String deleteOrder(ModelMap model,
			@PathVariable("orderId") int orderId) {

		storageSystem.delOrder(orderId);
		populateModel(model);
		return "order";
	}
	
	@RequestMapping(value = "/order/{orderId}/product", method = RequestMethod.POST)
	public String addProductToOrder(ModelMap model,
			@PathVariable("orderId") int orderId,
			@RequestParam("productid") int productId) {

		logger.debug("Adding product " + productId + " to order "
				+ orderId);
		storageSystem.addProductToOrder(orderId, productId);
		populateModel(model);
		return "order";
	}

	@RequestMapping(value = "/order/{orderId}/removeproduct/{productId}", method = RequestMethod.GET)
	public String removeProductFromOrder(ModelMap model,
			@PathVariable("orderId") int orderId,
			@PathVariable("productId") int productId) {

		logger.debug("Removing product " + productId + " from order "
				+ orderId);
		storageSystem.removeProductFromOrder(orderId, productId);
		populateModel(model);
		return "order";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String listProducts(ModelMap model) {

		model.addAttribute("message", "Products");
		populateModel(model);
		return "product";
	}

	@RequestMapping(value = "/product/new", method = RequestMethod.POST)
	public String createProduct(ModelMap model, 
			@RequestParam("name") String name,
			@RequestParam("type") String type) {

		storageSystem.addProduct(name, type);
		populateModel(model);
		return "product";
	}

	@RequestMapping(value = "/product/{productId}/delete", method = RequestMethod.GET)
	public String deleteProduct(ModelMap model,
			@PathVariable("productId") int productId) {

		storageSystem.delProduct(productId);
		populateModel(model);
		return "product";
	}
	
	private ModelMap populateModel(ModelMap model) {
		Collection<Customer> customers = storageSystem.getAllCustomers();
		model.addAttribute("customers", customers);
		Collection<Order> orders = storageSystem.getAllOrders();
		model.addAttribute("orders", orders);
		Collection<Product> products = storageSystem.getAllProduct();
		model.addAttribute("products", products);

		return model;
	}

}
