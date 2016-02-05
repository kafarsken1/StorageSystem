package no.dh.storagesystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thomas Iversen
 * @version $Id: Customer.java 05.02.2016 $
 */

@Entity
@Table(name = "file", catalog = "test")
@XmlRootElement(name = "file")
public class File {
	
	private int id;
	private String name;
	private String format;
	private byte[] data;
	
	// -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public File()
    {
    }

    public File( String name, String format, byte[] data )
    {
        this.name = name;
        this.format = format;
        this.data = data;
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

        final File other = (File) o;

        return name.equals( other.getName() );
    }

    // -------------------------------------------------------------------------
    // Setters and getters
    // -------------------------------------------------------------------------
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID", unique = true, nullable = false)
    public int getId()
    {
        return id;
    }
    
    public void setId( int id )
    {
        this.id = id;
    }
    
    @Column(name = "FILE_NAME", unique = false, nullable = false, length = 20)
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
    
    @Column(name = "FILE_FORMAT", nullable = false, length = 20)
    public String getFormat()
    {
        return format;
    }

    public void setFormat( String format )
    {
        this.format = format;
    }
    
    @Column(name = "FILE_DATA", nullable = false)
    @Lob
    public byte[] getData()
    {
    	return data;
    }
    
    public void setData( byte[] data )
    {
    	this.data = data;
    }
    
}
