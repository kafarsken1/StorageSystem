package no.dh.storagesystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thomas Iversen
 * @version $Id: Order.java 21.10.2015 $
 */
@XmlRootElement(name = "order")
public class Order
{
    private int id;

    /**
     * Required and unique.
     */
    private String orderNo;
    
    private Customer customer;
    
    private Date date;

    private Set<Product> products = new HashSet<Product>();


    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public Order()
    {
    }

    public Order( String orderNo )
    {
        this.orderNo = orderNo;
    }

    // -------------------------------------------------------------------------
    // Equals and hashcode
    // -------------------------------------------------------------------------

    @Override
    public int hashCode()
    {
        return orderNo.hashCode();
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

        if ( !(o instanceof Order) )
        {
            return false;
        }

        final Order other = (Order) o;

        return orderNo.equals( other.getOrderNo() );
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

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo( String orderNo )
    {
        this.orderNo = orderNo;
    }
    
    public Customer getCustomer()
    {
    	return customer;
    }
    
    public void setCustomer( Customer customer )
    {
    	this.customer = customer;
    }
    
    public Date getDate()
    {
    	return date;
    }
    
    public void setDate( Date date )
    {
    	this.date = date;
    }

    public Set<Product> getProducts()
    {
        //courses = new HashSet<Course>( courses ); // Rehash hack

        return products;
    }

    public void setProducts( Set<Product> products )
    {
        this.products = products;
    }

}
