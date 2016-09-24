package com.springmvc.fileupload.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.fileupload.form.FileUploadForm;
import com.springmvc.fileupload.model.Storage;
import com.springmvc.fileupload.service.StorageService;
import com.springmvc.fileupload.validator.FileValidator;

@Controller
@RequestMapping("/")
public class FileUploadController {

	@Autowired
	StorageService service;

	@Autowired
	FileValidator fileValidator;

	@InitBinder("uploadForm")
	protected void initBinderFileUploadForm(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	/*
	 * This method will list all existing documents.
	 */
	@RequestMapping(value = { "/", "list", "index" }, method = RequestMethod.GET)
	public String displayForm(ModelMap model) {

		List<Storage> documents = service.findAllStorage();
		model.addAttribute("documents", documents);
		return "fileUpload";
	}
	/*
	 * This method will provide the medium to add a new documents.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Accept=*/*")
	public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm,
			ModelMap map, BindingResult result, RedirectAttributes redirectAttributes) {
		Map<String, String> messages = new HashMap<String, String>();
		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return "redirect:/";
		}

		messages = addFiles(uploadForm);
		redirectAttributes.addFlashAttribute("messages", messages);
		return "redirect:/";
	}

	/*
	 * This method will download a document by it's docId.
	 */
	@RequestMapping(value = { "/download-file-{docId}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int docId,
			HttpServletResponse response) throws IOException {
		Storage document = service.findById(docId);
		response.setContentType(document.getMimetype());
		response.setContentLength(Math.toIntExact(document.getFilesize()));
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ document.getFilename() + "\"");

		FileCopyUtils.copy(document.getBinarylob(), response.getOutputStream());

		return "redirect:/";
	}
	/*
	 * This method will delete a document by it's docId.
	 */
	@RequestMapping(value = { "/delete-file-{docId}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int docId) {
		service.deleteStorageById(docId);
		return "redirect:/";
	}
	/*
	 * This method will save a documents and return response in json format.
	 */
	@RequestMapping(value= "/uploadFile" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, String>> uploadFile(@ModelAttribute("uploadForm") FileUploadForm uploadForm) {
		
		HttpHeaders httpHeader = new HttpHeaders();

		httpHeader.add("Access-Control-Allow-Origin", "*");

		return new ResponseEntity<Map<String, String>>(addFiles(uploadForm), httpHeader, HttpStatus.OK);
    }
	
	Map<String, String> addFiles(FileUploadForm uploadForm){
		Map<String, String> messages = new HashMap<String, String>();
		List<MultipartFile> files = uploadForm.getFiles();

		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				if (multipartFile.getSize() > 0) {
					String fileName = multipartFile.getOriginalFilename();
					Storage storage = new Storage();
					try {
						storage.setBinarylob(multipartFile.getBytes());
						storage.setFilename(multipartFile.getOriginalFilename());
						storage.setFilesize(multipartFile.getSize());
						storage.setMimetype(multipartFile.getContentType());
						int i = fileName.lastIndexOf('.');
						if (i > 0) {
							storage.setExtension(fileName.substring(i + 1));
						}

						service.saveStorage(storage);
			            messages.put("success", "Success");

					} catch (IOException e) {
						e.printStackTrace();
			            messages.put("error", "Error Occured");

					}
				}
		        

			}
		}
		else{
			messages.put("error", "Error Occured");
		}
		return messages;
	}

}
