package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import no.dh.storagesystem.dao.CourseDAO;
import no.dh.storagesystem.model.Course;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateCourseDAO implements CourseDAO {
	
	static Logger logger = Logger.getLogger(HibernateCourseDAO.class);
	
    private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

	public int saveCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(course);
		session.flush();
		return course.getId();
	}

	public Course getCourse(int id) {
		return (Course) sessionFactory.getCurrentSession().get( Course.class, id );
	}

	public Course getCourseByCourseCode(String courseCode) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("courseCode", courseCode));
		return (Course) criteria.uniqueResult();
	}

	public Course getCourseByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("name", name));
		return (Course) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<Course> getAllCourses() {
		return sessionFactory.getCurrentSession().createCriteria(Course.class).list();
	}

	public void delCourse(Course course) {
		sessionFactory.getCurrentSession().delete(course);
		sessionFactory.getCurrentSession().flush();
	}

}
