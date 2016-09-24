package com.springmvc.fileupload.dao;

import java.util.List;

import com.springmvc.fileupload.model.Storage;
/**
 * @author imranali
 *
 */
public interface StorageDao {

	Storage findById(int id);

	void saveStorage(Storage storage);
	
	void deleteStorageById(int id);
	
	List<Storage> findAllStorage();

	List<Storage> findStorageByExt(String ext);

}
