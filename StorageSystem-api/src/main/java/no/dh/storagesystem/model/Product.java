package no.dh.storagesystem.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Thomas Iversen
 * @version $Id: Product.java 21.10.2015 $
 */

@Entity
@Table(name = "product", catalog = "test", uniqueConstraints = {
		@UniqueConstraint(columnNames = "PRODUCT_NAME")})
@XmlRootElement(name = "product")
public class Product
{
    private int id;

    /**
     * Required and unique.
     */
    private String type;
    private String name;
    private int quantity;
    private int pallets;
    private int boxes;
    private String notice;
    private String shelfSpace;
    
    @JsonIgnore
    private Set<File> files = new HashSet<File>();
    

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public Product()
    {
    }

    public Product( String name, String type )
    {
    	this.name = name;
        this.type = type;
    }

    // -------------------------------------------------------------------------
    // Equals and hashcode
    // -------------------------------------------------------------------------

    @Override
    public int hashCode()
    {
        return type.hashCode();
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

        if ( !(o instanceof Product) )
        {
            return false;
        }

        final Product other = (Product) o;

        return name.equals( other.getName() );
    }

    // -------------------------------------------------------------------------
    // Setters and getters
    // -------------------------------------------------------------------------

    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }
    
    @Column(name = "PRODUCT_TYPE", unique = false, nullable = false, length = 20)
    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }
    
    @Column(name = "PRODUCT_NAME", unique = true, nullable = false, length = 20)
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
    
    @Column(name = "PRODUCT_QUANTITY", unique = false, nullable = true, length = 20)
    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }
    
    @Column(name = "PRODUCT_PALLETS", unique = false, nullable = true, length = 20)
    public int getPallets()
    {
        return pallets;
    }

    public void setPallets( int pallets )
    {
        this.pallets = pallets;
    }
    
    @Column(name = "PRODUCT_BOXES", unique = false, nullable = true, length = 20)
    public int getBoxes()
    {
        return boxes;
    }

    public void setBoxes( int boxes )
    {
        this.boxes = boxes;
    }
    
    @Column(name = "PRODUCT_NOTICE", unique = false, nullable = true, length = 20)
    public String getNotice()
    {
        return notice;
    }

    public void setNotice( String notice )
    {
        this.notice = notice;
    }
    
    @Column(name = "PRODUCT_SHELFSPACE", unique = false, nullable = true, length = 20)
    public String getShelfSpace()
    {
        return shelfSpace;
    }

    public void setShelfSpace( String shelfSpace )
    {
        this.shelfSpace = shelfSpace;
    }
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="product")
    public Set<File> getFiles()
    {
        return files;
    }

    public void setFiles( Set<File> files )
    {
        this.files = files;
    }

}
