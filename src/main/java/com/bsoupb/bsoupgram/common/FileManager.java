package com.bsoupb.bsoupgram.common;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public final static String FILE_UPLOAD_PATH = "C:\\Users\\qotn0\\OneDrive\\바탕 화면\\BAE\\springProject\\bsoupgram\\upload";
	
	public static String saveFile(int userId, MultipartFile file) {
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			return null;
		}
		
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		
		
		
	}
	
	
}
