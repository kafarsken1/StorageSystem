package no.dh.storagesystem.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thomas Iversen
 * @version $Id: Order.java 12.01.2016 $
 */

@Embeddable
@Table(name = "item", catalog = "test", 
uniqueConstraints = @UniqueConstraint(columnNames = "ORDERNO"))
@XmlRootElement(name = "order")
public class Item {
	
	    private int quantity;
	    
	    private Product product;
	    
	    public Item()
	    {
	    	
	    }
	    
	    public Item( Product product, int quantity )
	    {
	        this.product = product;
	        this.quantity = quantity;
	    }
	    
	    @Column(name = "ITEM_QUANTITY", unique = false, nullable = false, length = 10)
	    public int getQuantity(){
	    	return quantity;
	    }
	    
	    public void setQuantity(int quantity){
	    	this.quantity = quantity;
	    }
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    public Product getProduct(){
	    	return product;
	    }
	    
	    public void setProduct(Product product){
	    	this.product = product;
	    }
	    
	    


}
