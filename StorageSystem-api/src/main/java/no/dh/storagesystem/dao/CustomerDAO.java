package no.dh.storagesystem.dao;

import java.util.Collection;

import no.dh.storagesystem.model.Customer;

/**
 * @author Thomas Iversen
 * @version $Id: CustomerDAO.java 21.10.2015 $
 */
public interface CustomerDAO
{
    /**
     * Persists a customer. An unique id is generated if the object is persisted
     * for the first time, and which is both set in the given course object and
     * returned.
     * 
     * @param customer the customer to add for persistence.
     * @return the id of the customer.
     */
    int saveCustomer( Customer customer );

    /**
     * Returns a customer.
     * 
     * @param id the id of the customer to return.
     * @return the customer or null if it doesn't exist.
     */
    Customer getCustomer( int id );

    /**
     * Returns a customer with a specific name.
     * 
     * @param name the name of the customer to return.
     * @return the name or null if it doesn't exist.
     */
    Customer getCustomerByName( String name );

    /**
     * Returns a customer with a specific phone number.
     * 
     * @param phone the phone number of the customer to return.
     * @return the phone number or null if it doesn't exist.
     */
    Customer getCustomerByPhone( String phone );

    /**
     * Returns all customers.
     * 
     * @return all customers.
     */
    Collection<Customer> getAllCustomers();

    /**
     * Deletes a customer.
     * 
     * @param customer the customer to delete.
     */
    void delCustomer( Customer customer );
}
