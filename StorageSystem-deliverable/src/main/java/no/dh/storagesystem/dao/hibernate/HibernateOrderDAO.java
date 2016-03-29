package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import no.dh.storagesystem.dao.OrderDAO;
import no.dh.storagesystem.model.Customer;
import no.dh.storagesystem.model.Order;

@Repository("orderDAO")
public class HibernateOrderDAO extends BaseDAO implements OrderDAO{
	
	static Logger logger = Logger.getLogger(HibernateOrderDAO.class);
	
	@Override
	public int saveOrder(Order order) {
		getSession().saveOrUpdate(order);
		getSession().flush();
		return order.getId();
	}

	@Override
	public Order getOrder(int id) {
		return (Order) getSession().get( Order.class, id );
	}

	@Override
	public Order getOrderByOrderNo(String orderNo) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("orderNo", orderNo));
		return (Order) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Order> getOrdersByCustomer(Customer customer) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("customer", customer));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Order> getOrdersByDate(Date date) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("created", date));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Order> getAllOrders() {
		return getSession().createCriteria(Order.class).list();
	}

	@Override
	public void delOrder(Order order) {
		getSession().delete(order);
		getSession().flush();
	}

}
