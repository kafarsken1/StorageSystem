package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import no.dh.storagesystem.dao.OrderDAO;
import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Order;

public class HibernateOrderDAO implements OrderDAO{
	
	static Logger logger = Logger.getLogger(HibernateOrderDAO.class);
	
    private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public int saveOrder(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(order);
		session.flush();
		return order.getId();
	}

	@Override
	public Order getOrder(int id) {
		return (Order) sessionFactory.getCurrentSession().get( Order.class, id );
	}

	@Override
	public Order getOrderByOrderNo(String orderNo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("orderNo", orderNo));
		return (Order) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Order> getOrdersByCustomer(Customer customer) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("customer", customer));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Order> getOrdersByDate(Date date) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("date", date));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Order> getAllOrders() {
		return sessionFactory.getCurrentSession().createCriteria(Order.class).list();
	}

	@Override
	public void delOrder(Order order) {
		sessionFactory.getCurrentSession().delete(order);
		sessionFactory.getCurrentSession().flush();
	}

}
