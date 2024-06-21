package com.poscodx.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource("classpath:com/poscodx/jblog/config/web/fileupload.properties")
public class FileUploadService {
	@Autowired
	private Environment env;
	
	public String restore(String id, MultipartFile file) {
		String url = null;
		
		try {
			File uploadDirectory = new File(env.getProperty("fileupload.uploadLocation"));
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			
			if(file.isEmpty()) {
				return url;
			}
			
			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf(".")+1);
			String saveFilename = generateSaveFilename(id, extName);
			Long fileSize = file.getSize();
			
			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(env.getProperty("fileupload.uploadLocation") + "/" + saveFilename);
			os.write(data);
			os.close();
			
			url = env.getProperty("fileupload.resourceUrl") + "/" + saveFilename;
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
		return url;
	}

	private String generateSaveFilename(String id, String extName) {
		String filename = "";
		
		filename += id;
		filename += ("." + extName);
		return filename;
	}

}
