package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import no.dh.storagesystem.dao.DegreeDAO;
import no.dh.storagesystem.model.Degree;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateDegreeDAO implements DegreeDAO {
	
	static Logger logger = Logger.getLogger(HibernateDegreeDAO.class);
	
    private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

	public int saveDegree(Degree degree) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(degree);
		session.flush();
		return degree.getId();
	}

	public Degree getDegree(int id) {
		return (Degree) sessionFactory.getCurrentSession().get( Degree.class, id );
	}

	public Degree getDegreeByType(String type) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Degree.class);
		criteria.add(Restrictions.eq("type", type));
		return (Degree) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<Degree> getAllDegrees() {
		return sessionFactory.getCurrentSession().createCriteria(Degree.class).list();
	}

	public void delDegree(Degree degree) {
		sessionFactory.getCurrentSession().delete(degree);
		sessionFactory.getCurrentSession().flush();
	}

}
