package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import no.dh.storagesystem.dao.CustomerDAO;
import no.dh.storagesystem.model.Customer;

@Repository("customerDAO")
public class HibernateCustomerDAO extends BaseDAO implements CustomerDAO{
	
	static Logger logger = Logger.getLogger(HibernateCustomerDAO.class);
	
	@Override
	public int saveCustomer(Customer customer) {
		getSession().saveOrUpdate(customer);
		getSession().flush();
		return customer.getId();
	}

	@Override
	public Customer getCustomer(int id) {
		return (Customer) getSession().get( Customer.class, id );
	}

	@Override
	public Customer getCustomerByName(String name) {
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("name", name));
		return (Customer) criteria.uniqueResult();
	}

	@Override
	public Customer getCustomerByPhone(String phone) {
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("phone", phone));
		return (Customer) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Customer> getAllCustomers() {
		return getSession().createCriteria(Customer.class).list();
	}

	@Override
	public void delCustomer(Customer customer) {
		getSession().delete(customer);
		getSession().flush();
	}

}
