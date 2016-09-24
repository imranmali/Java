package com.springmvc.fileupload.service;

import java.util.List;

import com.springmvc.fileupload.model.Storage;
/**
 * @author imranali
 *
 */
public interface StorageService {

	Storage findById(int id);
	
	void saveStorage(Storage storage);
	
	void updateStorage(Storage storage);
	
	void deleteStorageById(int id);

	List<Storage> findAllStorage(); 
	
	List<Storage> findStorageByExtension(String ext);

}
