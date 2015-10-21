package no.dh.storagesystem.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thomas Iversen
 * @version $Id: Product.java 21.10.2015 $
 */
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

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }
    
    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
    
    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }
    
    public int getPallets()
    {
        return pallets;
    }

    public void setPallets( int pallets )
    {
        this.pallets = pallets;
    }
    
    public int getBoxes()
    {
        return boxes;
    }

    public void setBoxes( int boxes )
    {
        this.boxes = boxes;
    }
    
    public String getNotice()
    {
        return notice;
    }

    public void setNotice( String notice )
    {
        this.notice = notice;
    }
    
    public String getShelfSpace()
    {
        return shelfSpace;
    }

    public void setShelfSpace( String shelfSpace )
    {
        this.shelfSpace = shelfSpace;
    }

}
