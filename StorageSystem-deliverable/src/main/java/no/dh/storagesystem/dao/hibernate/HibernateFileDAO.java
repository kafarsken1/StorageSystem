package no.dh.storagesystem.dao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import no.dh.storagesystem.dao.FileDAO;
import no.dh.storagesystem.model.File;
import no.dh.storagesystem.model.Product;

@Repository("fileDAO")
public class HibernateFileDAO extends BaseDAO implements FileDAO{

	@Override
	public int saveFile(File file) {
		getSession().saveOrUpdate(file);
		getSession().flush();
		return file.getId();
	}

	@Override
	public File getFile(int id) {
		return (File) getSession().get( File.class, id );
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<File> getFilesByProduct(Product product) {
		Criteria criteria = getSession().createCriteria(File.class);
		criteria.add(Restrictions.eq("product", product));
		return criteria.list();
	}
	
	@Override
	public void delFile(File file) {
		getSession().delete(file);
		getSession().flush();
	}

}
