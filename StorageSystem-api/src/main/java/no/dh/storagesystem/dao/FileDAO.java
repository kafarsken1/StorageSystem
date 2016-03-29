package no.dh.storagesystem.dao;

import java.util.Collection;

import no.dh.storagesystem.model.File;
import no.dh.storagesystem.model.Product;

/**
 * @author Thomas Iversen
 * @version $Id: CustomerDAO.java 05.02.2016 $
 */
public interface FileDAO {
	
	/**
     * Persists a file. An unique id is generated if the object is persisted
     * for the first time, and which is both set in the given course object and
     * returned.
     * 
     * @param file the file to add for persistence.
     * @return the id of the file.
     */
    int saveFile( File file );
    
    /**
     * Returns a file.
     * 
     * @param id the id of the file to return.
     * @return the file or null if it doesn't exist.
     */
    File getFile( int id );
    
    /**
     * Returns all files with a specific product.
     * 
     * @param product the product of the files to return.
     * @return the files or null if it doesn't exist.
     */
    Collection<File> getFilesByProduct( Product product );
    
    /**
     * Deletes a file.
     * 
     * @param file the file to delete.
     */
    void delFile( File file );

}
