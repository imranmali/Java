package com.springmvc.fileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.fileupload.dao.StorageDao;
import com.springmvc.fileupload.model.Storage;
/**
 * @author imranali
 *
 */

@Service("storageService")
@Transactional
public class StorageServiceImpl implements StorageService {

	@Autowired
	private StorageDao dao;
	
	public Storage findById(int id) {
		return dao.findById(id);
	}

	public void saveStorage(Storage storage) {
		dao.saveStorage(storage);
	}
	
	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateStorage(Storage storage) {
		Storage entity = dao.findById(storage.getId());
		if(entity!=null){
			entity.setFilename(storage.getFilename());
			entity.setExtension(storage.getExtension());
			entity.setFilesize(storage.getFilesize());
			entity.setMimetype(storage.getMimetype());
			entity.setBinarylob(storage.getBinarylob());
		}
	}

	public void deleteStorageById(int id) {
		dao.deleteStorageById(id);
	}

	public List<Storage> findAllStorage() {
		return dao.findAllStorage();
	}

	public List<Storage> findStorageByExtension(String ext) {
		return dao.findStorageByExt(ext);
	}

}
