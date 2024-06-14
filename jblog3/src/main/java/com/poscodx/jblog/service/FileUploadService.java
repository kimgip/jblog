package com.poscodx.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String SAVE_PATH="/jblog-uploads";
	private static String URL_PATH="/assets/upload-images/"; // 가상 url
	
	public String restore(String id, MultipartFile file) {
		String url = null;
		
		try {
			File uploadDirectory = new File(SAVE_PATH);
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
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			url = URL_PATH + "/" + saveFilename;
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
