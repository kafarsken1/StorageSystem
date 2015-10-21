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
    private Set<Student> attendants = new HashSet<Student>();

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public Customer()
    {
    }

    public Customer( String courseCode, String name )
    {
        this.courseCode = courseCode;
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

        result = result * prime + courseCode.hashCode();
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

        if ( !(o instanceof Course) )
        {
            return false;
        }

        final Course other = (Course) o;

        return courseCode.equals( other.getCourseCode() ) && name.equals( other.getName() );
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

    public String getCourseCode()
    {
        return courseCode;
    }

    public void setCourseCode( String courseCode )
    {
        this.courseCode = courseCode;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Set<Student> getAttendants()
    {
        //attendants = new HashSet<Student>( attendants ); // Rehash hack

        return attendants;
    }

    public void setAttendants( Set<Student> attendants )
    {
        this.attendants = attendants;
    }
}
