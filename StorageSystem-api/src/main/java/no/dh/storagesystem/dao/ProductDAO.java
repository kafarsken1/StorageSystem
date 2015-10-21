package no.dh.storagesystem.dao;

import java.util.Collection;

import no.dh.storagesystem.model.Product;

/**
 * @author Thomas Iversen
 * @version $Id: ProductDAO.java 21.10.2015 $
 */
public interface ProductDAO
{
    /**
     * Persists a product. An unique id is generated if the object is persisted
     * for the first time, and which is both set in the given student object and
     * returned.
     * 
     * @param product the product to add for persistence.
     * @return the id of the product.
     */
    int saveProduct( Product product );

    /**
     * Returns a product.
     * 
     * @param id the id of the product to return.
     * @return the product or null if it doesn't exist.
     */
    Product getProduct( int id );

    /**
     * Returns a product with a specific name.
     * 
     * @param name the name of the product to return.
     * @return the product or null if it doesn't exist.
     */
    Product getProductByName( String name );
    
    /**
     * Returns a product with a specific shelf space.
     * 
     * @param shelfSpace the shelf space of the product to return.
     * @return the product or null if it doesn't exist.
     */
    Product getProductByShelfSpace( String shelfSpace );
    
    /**
     * Returns all products with a specific type.
     * 
     * @param type the type of the products to return.
     * @return the products or null if it doesn't exist.
     */
    Collection<Product> getProductsByType( String type );

    /**
     * Returns all products.
     * 
     * @return all products.
     */
    Collection<Product> getAllProducts();

    /**
     * Deletes a product.
     * 
     * @param product the product to delete.
     */
    void delProduct( Product product );
}
