package no.dh.storagesystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Thomas Iversen
 * @version $Id: Order.java 21.10.2015 $
 */

@Entity
@Table(name = "order", catalog = "test", 
uniqueConstraints = @UniqueConstraint(columnNames = "ORDERNO"))
@XmlRootElement(name = "order")
public class Order
{
    private int id;

    /**
     * Required and unique.
     */
    private String orderNo;
    
    private Customer customer;
    
    private Date created;
    
    private Date updated;

    private Set<Item> items = new HashSet<Item>();
    
 /*   @PrePersist
    protected void onCreate() {
      created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
      updated = new Date();
    }
*/

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
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID", unique = true, nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    @Column(name = "ORDERNO", unique = true, nullable = false, length = 20)
    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo( String orderNo )
    {
        this.orderNo = orderNo;
    }
    
    @ManyToOne//(cascade=CascadeType.ALL)
	//@JoinColumn(name = "ORDER_ID", nullable = false, insertable=false, updatable=false)
    public Customer getCustomer()
    {
    	return customer;
    }
    
    public void setCustomer( Customer customer )
    {
    	this.customer = customer;
    }
    
    @Column(name = "CREATED_DATE", updatable = false)
   // @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    public Date getCreated()
    {
    	return created;
    }
    
    public void setCreated( Date created )
    {
    	this.created = created;
    }
    
    @Column(name = "UPDATED_DATE", insertable = false)
   // @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    public Date getUpdated()
    {
    	return updated;
    }
    
    public void setUpdated( Date updated )
    {
    	this.updated = updated;
    }

    @ElementCollection(fetch = javax.persistence.FetchType.LAZY)
    @CollectionTable( 
            name = "item_products", 
            joinColumns = @JoinColumn( name = "ORDER_ID" ) 
       )
    public Set<Item> getItems()
    {
        //courses = new HashSet<Course>( courses ); // Rehash hack

        return items;
    }

    public void setItems( Set<Item> items )
    {
        this.items = items;
    }

}
