package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import no.dh.storagesystem.dao.StudentDAO;
import no.dh.storagesystem.model.Student;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class HibernateStudentDAO implements StudentDAO {
	
	static Logger logger = Logger.getLogger(HibernateStudentDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }
	
	public int saveStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
		session.flush();
		return student.getId();
	}

	public Student getStudent(int id) {
		return (Student) sessionFactory.getCurrentSession().get( Student.class, id );
	}

	public Student getStudentByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("name", name));
		return (Student) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<Student> getAllStudents() {
		return sessionFactory.getCurrentSession().createCriteria(Student.class).list();
	} 
		

	public void delStudent(Student student) {
		sessionFactory.getCurrentSession().delete(student);
		sessionFactory.getCurrentSession().flush();
	}

}
