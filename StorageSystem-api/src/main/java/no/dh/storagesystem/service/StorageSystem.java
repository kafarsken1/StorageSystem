package no.dh.storagesystem.service;

import java.util.Collection;
import java.util.Date;

import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.File;
import no.dh.storagesystem.model.Order;
import no.dh.storagesystem.model.Product;

/**
 * @author Thomas Iversen
 * @version $Id: StorageSystem.java 21.10.2015 $
 */
public interface StorageSystem
{
    /**
     * Adds a customer.
     * 
     * @param name the name of the customer to add.
     * @param phone the phone of the customer to add.
     * @param email the email of the customer to add.
     * @param address the address of the customer to add.
     * @return the generated id of the added customer.
     */
    int addCustomer( String name, String phone, String email, String address );

    /**
     * Updates a customer.
     * 
     * @param customerId the id of the customer to update.
     * @param name the name of the customer to update.
     * @param phone the phone of the customer to update.
     * @param address the address of the customer to update.
     */
    void updateCustomer( int customerId, String name, String phone, String email, String address );

    /**
     * Returns a customer.
     * 
     * @param customerId the id of the customer to return.
     * @return the customer or null if it doesn't exist.
     */
    Customer getCustomer( int customerId );

    /**
     * Returns a customer with a specific name.
     * 
     * @param name the name of the customer to return.
     * @return the customer or null if it doesn't exist.
     */
    Customer getCustomerByName( String name );

    /**
     * Returns a customer with a specific phone number.
     * 
     * @param phone the phone number of the customer that needs to be found
     * @return the customer or null if it doesn't exist.
     */
    Customer getCustomerByPhone( String phone );

    /**
     * Returns all customers.
     * 
     * @return all customers or an empty Collection if no course exists.
     */
    Collection<Customer> getAllCustomers();

    /**
     * Removes all references to the customer from orders and
     * deletes the customer.
     * 
     * @param customerId the id of the customer to delete.
     */
    void delCustomer( int customerId );

    /**
     * Adds an order to a customer and a customer to an order.
     * 
     * @param customerId the id of the customer.
     * @param orderId the id of the order.
     */
    void addOrderToCustomer( int customerId, int orderId );

    /**
     * Removes an order from a customer and a customer from an order.
     * 
     * @param customerId the id of the customer.
     * @param orderId the id of the order.
     */
    void removeOrderFromCustomer( int customerId, int orderId );

    /**
     * Adds an order.
     * 
     * @param orderNo the order number of the order to add.
     * @return the generated id of the added order.
     */
    int addOrder( String orderNo );

    /**
     * Updates an order.
     * 
     * @param orderId the id of the order to update.
     * @param orderNo the order number of the order to update.
     * @param Customer the customer of the order to update.
     * @param date the date of the order to update.
     */
    void updateOrder( int orderId, String orderNo, Customer customer);

    /**
     * Returns an order.
     * 
     * @param orderId the id of the order to return.
     * @return the order or null if it doesn't exist.
     */
    Order getOrder( int orderId );

    /**
     * Returns an order with a specific order number.
     * 
     * @param orderNo the order number of the order to return.
     * @return the order or null if it doesn't exist.
     */
    Order getOrderByOrderNo( String orderNo );

    /**
     * Returns all orders.
     * 
     * @return all orders or an empty Collection if no customer exists.
     */
    Collection<Order> getAllOrders();
    
    /**
     * Returns orders with a specific customer.
     * 
     * @return orders or an empty Collection if no customer exists.
     */
    Collection<Order> getOrdersByCustomer( Customer customer);
    
    /**
     * Returns all orders with a date.
     * 
     * @param date the date of the orders to return.
     * @return the orders or null if it doesn't exist.
     */
    Collection<Order> getOrdersByDate( Date date );

    /**
     * Removes all references to the order from customer and deletes the
     * order.
     * 
     * @param orderId the id of the order to delete.
     */
    void delOrder( int orderId );

    /**
     * Adds a product to an order.
     * 
     * @param orderId the id of the order.
     * @param productId the id of the product.
     */
    void addItemToOrder( int orderId, int productId, int quantity );

    /**
     * Removes a product from an order.
     * 
     * @param orderId the id of the order.
     * @param productId the id of the product.
     */
    void removeItemFromOrder( int orderId, int productId );

    /**
     * Adds a product.
     * 
     * @param name the name of the product to add.
     * @return the generated id of the added product.
     */
    int addProduct( String name, String type );

    /**
     * Updates a product.
     * 
     * @param productId the id of the product to update.
     * @param name the name of the product to update.
     */
    void updateProduct( int productId, String name, String type, int quantity, int pallets, int boxes, String notice, String shelfSpace );

    /**
     * Returns a product.
     * 
     * @param productId the id of the product to return.
     * @return the product or null if it doesn't exist.
     */
    Product getProduct( int productId );

    /**
     * Returns a product with a specific name.
     * 
     * @param name the name of the product to return.
     * @return the product or null if it doesn't exist.
     */
    Product getProductByName( String name );
    
    /**
     * Returns a product with a specific shelf space.
     * 
     * @param shelfSpace the shelf space of the product to return.
     * @return the product or null if it doesn't exist.
     */
    Product getProductByShelfSpace( String shelfSpace );
    
    /**
     * Returns products with a specific type.
     * 
     * @return products or an empty Collection if no student exists.
     */
    Collection<Product> getProductsByType( String type);
    
    /**
     * Returns all product.
     * 
     * @return all products or an empty Collection if no student exists.
     */
    Collection<Product> getAllProduct();

    /**
     * Removes all references to the product from orders and deletes the
     * product.
     * 
     * @param productId the id of the product to delete.
     */
        
    void delProduct( int productId );
    
    /**
     * Returns a file.
     * 
     * @param fileId the id of the file to return.
     * @return the file or null if it doesn't exist.
     */
    File getFile( int fileId );
    
    /**
     * Adds a file.
     * 
     * @param name the name of the file to add.
     * @param format the format of the file to add.
     * @param data the data of the file to add
     * @return the generated id of the added product.
     */
    int addFile( String name, String format, byte[] data );
    
    /**
     * Returns files with a specific product.
     * 
     * @return files or an empty Collection if no files exists.
     */
    Collection<File> getFilesByProduct( Product product );
    
    /**
     * Removes all references to the file from product and deletes the
     * file.
     * 
     * @param fileId the id of the file to delete.
     */
        
    void delFile( int fileId );

}
