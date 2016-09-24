package com.springmvc.fileupload.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.fileupload.form.FileUploadForm;

@Component
public class FileValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return FileUploadForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		
		FileUploadForm files = (FileUploadForm) obj;
		int index = 0;
		for(MultipartFile file:files.getFiles()){
			if (file != null) {
				if (file.getSize() == 0) {
					errors.rejectValue("files["+index+"].file", "missing.file");
				}
			}
			index++;
		}
	}
	
}
