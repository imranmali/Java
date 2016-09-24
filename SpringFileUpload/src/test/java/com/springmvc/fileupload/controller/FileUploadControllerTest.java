package com.springmvc.fileupload.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.springmvc.fileupload.model.Storage;
import com.springmvc.fileupload.service.StorageService;

public class FileUploadControllerTest {

	@Mock
	StorageService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	FileUploadController fileUploadController;
	
	@Spy
	List<Storage> files = new ArrayList<Storage>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		files = getFileList();
	}
	
	@Test
	public void listStorage(){
		when(service.findAllStorage()).thenReturn(files);
		Assert.assertEquals(fileUploadController.displayForm(model), "list");
		Assert.assertEquals(model.get("documents"), files);
		verify(service, atLeastOnce()).findAllStorage();
	}
	
	@Test
	public void newStorage(){
		Assert.assertEquals(fileUploadController.save(null,model,result,null),"redirect:/");
		Assert.assertNotNull(model.get("documents"));
	}


	@Test
	public void saveStorageWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveStorage(any(Storage.class));
		Assert.assertEquals(fileUploadController.save(null, model,result,null), "redirect:/");
	}

	@Test
	public void deleteStorage(){
		doNothing().when(service).deleteStorageById(anyInt());
		Assert.assertEquals(fileUploadController.deleteDocument(123), "redirect:/");
	}

	public List<Storage> getFileList() {
		
		File folder = new File("D:/Demo/FileUpload");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        Storage storage = new Storage();
		        storage.setFilename(file.getName());
		        Path fileName = Paths.get(file.getPath());
				try {
					storage.setBinarylob(Files.readAllBytes(fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
				files.add(storage);
		    }
		}
		
		return files;
	}
}
