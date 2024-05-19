package com.mysite.sbb.file;






import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@PropertySource("/config.properties")

@RequiredArgsConstructor
@Slf4j
@Service
public class FileService {
	
	//.properties 값 가져오기
	@Autowired
    Environment env;
	
	private final FileRepository fileRepository;
	//result 0 = 실패
	//result 1 = 성공
	
	public int uploadallfile(MultipartFile file,String location) {
	
		int result=0;
		
		//TODO DB작업 예정.
		
		
		
		 if (!file.isEmpty()) {
	            try {
	                // 파일을 저장할 경로 지정
	         
	            	//디렉토리 존재하지 않으면 생성
	                if(! new File(location).exists())
	                {
	                    new File(location).mkdir();
	                }
	                // 파일 저장
	                String orgName = file.getOriginalFilename();
	                String filePath = location +"/"+ orgName;
	                
	                //파일 객체생성
	                File dest = new File(filePath);
	               //TODO 파일이 이미 존재하는경우 로직 추가예정
	                
	                
	                //객체대로 실제파일 생성	
	                file.transferTo(dest);
	                result = 1;
	                log.debug("message", "파일 업로드 성공: " + orgName);
	            } catch (IOException e) {
	                e.printStackTrace();
	                log.debug("message", "파일 업로드 실패: " + file.getOriginalFilename());
	                result = 0;
	            }
	        } else {
	        	log.debug("message", "업로드할 파일을 선택해주세요.");
	        	result = 0;
	        }
		return result;
	}
	

}
