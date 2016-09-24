/**
 * 
 */
package com.springmvc.fileupload.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.springmvc.fileupload.model.Storage;

/**
 * @author imranali
 *
 */
@Repository("storageDao")
public class StorageDaoImpl extends AbstractDao<Integer, Storage>  implements StorageDao {

	public Storage findById(int id) {
		return getByKey(id);
	}

	public void saveStorage(Storage storage) {
		persist(storage);
	}

	public void deleteStorageById(int id) {
		Query query = getSession().createSQLQuery("delete from Storage where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Storage> findAllStorage() {
		Criteria criteria = createEntityCriteria();
		return (List<Storage>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Storage> findStorageByExt(String ext) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("extension", ext));
		return (List<Storage>) criteria.list();
	}

}
