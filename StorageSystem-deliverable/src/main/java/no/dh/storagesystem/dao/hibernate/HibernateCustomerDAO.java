package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import no.dh.storagesystem.dao.CustomerDAO;
import no.dh.storagesystem.model.Customer;

public class HibernateCustomerDAO implements CustomerDAO{
	
	static Logger logger = Logger.getLogger(HibernateCustomerDAO.class);
	
    private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public int saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
		session.flush();
		return customer.getId();
	}

	@Override
	public Customer getCustomer(int id) {
		return (Customer) sessionFactory.getCurrentSession().get( Customer.class, id );
	}

	@Override
	public Customer getCustomerByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("name", name));
		return (Customer) criteria.uniqueResult();
	}

	@Override
	public Customer getCustomerByPhone(String phone) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("phone", phone));
		return (Customer) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Customer> getAllCustomers() {
		return sessionFactory.getCurrentSession().createCriteria(Customer.class).list();
	}

	@Override
	public void delCustomer(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);
		sessionFactory.getCurrentSession().flush();
	}

}
