package com.bsoupb.bsoupgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public final static String FILE_UPLOAD_PATH = "C:\\Users\\qotn0\\OneDrive\\바탕 화면\\BAE\\springProject\\bsoupgram\\upload";	// 실제 저장할 경로
	
	public static String saveFile(int userId, MultipartFile file) {  // 저장된 파일에 접근하기 위한 url 경로를 return
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();		// 각 파일들을 구분할 수 있게 하기 위한 폴더 이름
		
		// 폴더 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
	
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			// 파일 저장 실패
			e.printStackTrace();
			return null;
		}

		// 클라이언트에서 접근 가능한 경로 - 데이터베이스에 저장
		// 클라이어느에서 접근 가능한 경로 리턴
		
		// 파일 경로 : C:\\Users\\qotn0\\OneDrive\\바탕 화면\\BAE\\springProject\\memo\\upload/2_32908531908/test.png
		// url 경로 규칙 : /images/2_32908531908/test.png
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	
	}
	
	public static boolean removeFile(String filePath) {
		
		if(filePath == null) {
			return false;
		}
		
		// 삭제 대상 파일 경로
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(fullFilePath);
		
		// 파일이 존재하는지
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		// 디렉토리 삭제
		Path dirPath = path.getParent();
		
		if(Files.exists(dirPath)) {
			try {
				Files.delete(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
				
				return false;
			}
		}
		
		return true;
		
	}
	
}
