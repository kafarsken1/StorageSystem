package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import no.dh.storagesystem.dao.ProductDAO;
import no.dh.storagesystem.model.Product;

@Repository("productDAO")
public class HibernateProductDAO extends BaseDAO implements ProductDAO{
	
	static Logger logger = Logger.getLogger(HibernateProductDAO.class);
	
	@Override
	public int saveProduct(Product product) {
		getSession().saveOrUpdate(product);
		getSession().flush();
		return product.getId();
	}

	@Override
	public Product getProduct(int id) {
		return (Product) getSession().get( Product.class, id );
	}

	@Override
	public Product getProductByName(String name) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("name", name));
		return (Product) criteria.uniqueResult();
	}

	@Override
	public Product getProductByShelfSpace(String shelfSpace) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("shelfSpace", shelfSpace));
		return (Product) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> getProductsByType(String type) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("type", type));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> getAllProducts() {
		return getSession().createCriteria(Product.class).list();
	}

	@Override
	public void delProduct(Product product) {
		getSession().delete(product);
		getSession().flush();
	}

}
