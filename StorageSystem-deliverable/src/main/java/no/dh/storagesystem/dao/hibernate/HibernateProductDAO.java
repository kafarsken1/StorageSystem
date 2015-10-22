package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import no.dh.storagesystem.dao.ProductDAO;
import no.dh.storagesystem.model.Product;

public class HibernateProductDAO implements ProductDAO{
	
	static Logger logger = Logger.getLogger(HibernateProductDAO.class);
	
    private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public int saveProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
		return product.getId();
	}

	@Override
	public Product getProduct(int id) {
		return (Product) sessionFactory.getCurrentSession().get( Product.class, id );
	}

	@Override
	public Product getProductByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("name", name));
		return (Product) criteria.uniqueResult();
	}

	@Override
	public Product getProductByShelfSpace(String shelfSpace) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("shelfSpace", shelfSpace));
		return (Product) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> getProductsByType(String type) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("type", type));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> getAllProducts() {
		return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
	}

	@Override
	public void delProduct(Product product) {
		sessionFactory.getCurrentSession().delete(product);
		sessionFactory.getCurrentSession().flush();
	}

}
