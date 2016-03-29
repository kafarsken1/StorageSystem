package no.dh.storagesystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Thomas Iversen
 * @version $Id: Customer.java 20.10.2015 $
 */

@Entity
@Table(name = "customer", catalog = "test", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CUSTOMER_NAME")})
@XmlRootElement(name = "customer")
public class Customer
{
    private int id;

    /**
     * Required and unique.
     */

    private String name;
    private String phone;  
    private String email;
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
    
    public Customer( String name, String phone, String email, String address )
    {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    @Column(name = "CUSTOMER_NAME", unique = true, nullable = false, length = 20)
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @Column(name = "CUSTOMER_PHONE", unique = false, nullable = true, length = 12)
    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }
    
    @Column(name = "CUSTOMER_EMAIL", unique = false, nullable = true, length = 30)
    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }
    
    @Column(name = "CUSTOMER_ADDRESS", unique = false, nullable = true, length = 30)
    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
    public Set<Order> getOrders()
    {
        return orders;
    }

    public void setOrders( Set<Order> orders )
    {
        this.orders = orders;
    }
}
