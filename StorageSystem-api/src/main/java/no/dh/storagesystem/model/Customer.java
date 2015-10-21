package no.dh.storagesystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Thomas Iversen
 * @version $Id: Customer.java 20.10.2015 $
 */
@XmlRootElement(name = "customer")
public class Customer
{
    private int id;

    /**
     * Required and unique.
     */

    private String name;
    private String phone;   
    private String address; 

    @JsonIgnore
    private Set<Order> orders = new HashSet<Order>();

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public Customer()
    {
    }

    public Customer( String name )
    {
        this.name = name;
    }

    // -------------------------------------------------------------------------
    // Equals and hashcode
    // -------------------------------------------------------------------------

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = result * prime + name.hashCode();

        return result;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }

        if ( o == null )
        {
            return false;
        }

        if ( !(o instanceof Customer) )
        {
            return false;
        }

        final Customer other = (Customer) o;

        return name.equals( other.getName() );
    }

    // -------------------------------------------------------------------------
    // Setters and getters
    // -------------------------------------------------------------------------

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }
    
    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }
    
    public Set<Order> getOrders()
    {
        //attendants = new HashSet<Student>( attendants ); // Rehash hack

        return orders;
    }

    public void setOrders( Set<Order> orders )
    {
        this.orders = orders;
    }
}
