package no.dh.storagesystem.dao;

import java.util.Collection;
import java.util.Date;

import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Order;

/**
 * @author Thomas Iversen
 * @version $Id: OrderDAO.java 21.10.2015 $
 */
public interface OrderDAO
{
    /**
     * Persists a order. An unique id is generated if the object is persisted
     * for the first time, and which is both set in the given degree object and
     * returned.
     * 
     * @param order the order to add for persistence.
     * @return the id of the order.
     */
    int saveOrder( Order order );

    /**
     * Returns a order.
     * 
     * @param id the id of the order to return.
     * @return the order or null if it doesn't exist.
     */
    Order getOrder( int id );

    /**
     * Returns a order with a specific order number.
     * 
     * @param orderNo the order number of the order to return.
     * @return the order or null if it doesn't exist.
     */
    Order getOrderByOrderNo( String orderNo );

    /**
     * Returns all orders with a specific customer.
     * 
     * @param customer the customer of the orders to return.
     * @return the orders or null if it doesn't exist.
     */
    Collection<Order> getOrdersByCustomer( Customer customer );
    
    /**
     * Returns all orders with a date.
     * 
     * @param date the date of the orders to return.
     * @return the orders or null if it doesn't exist.
     */
    Collection<Order> getOrdersByDate( Date date );
    
    /**
     * Returns all orders.
     * 
     * @return all orders.
     */
    Collection<Order> getAllOrders();

    /**
     * Deletes a degree.
     * 
     * @param degree the degree to delete.
     */
    void delOrder( Order order );
}
