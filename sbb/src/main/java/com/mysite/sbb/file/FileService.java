package com.mysite.sbb.file;






import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.sbb.user.SiteUser;

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
	
	public String uploadallfile(MultipartFile fileInfo,String location,String user) {
	
		FileTb fl = new FileTb();
		String SuccessFileId="";
        String orgName = fileInfo.getOriginalFilename();
        String filePath = location +"/"+ orgName;
		
		 if (!fileInfo.isEmpty()) {
	            try {
	                // 파일을 저장할 경로 지정
	         
	            	//디렉토리 존재하지 않으면 생성
	                if(! new File(location).exists())
	                {
	                    new File(location).mkdir();
	                }
	                // 파일 저장

	                
	                //파일 객체생성
	                File dest = new File(filePath);
	               //TODO 파일이 이미 존재하는경우 로직 추가예정
	                
	                
	                //객체대로 실제파일 생성	
	                fileInfo.transferTo(dest);
	                SuccessFileId = "";
	                log.debug("message", "파일 업로드 성공: " + orgName);
	            } catch (IOException e) {
	                e.printStackTrace();
	                log.debug("message", "파일 업로드 실패: " + fileInfo.getOriginalFilename());
	                SuccessFileId = "FAIL";
	            }
	        } else {
	        	log.debug("message", "업로드할 파일을 선택해주세요.");
	        	SuccessFileId = "FAIL";
	        }
			//TODO DB작업 예정.
			try {
				
				fl.setCreatorId(user);
				fl.setFileId("");
				fl.setFileName(orgName);
				fl.setExtention("");
				fl.setLocation("");
				fl.setServerPath("");
				fl.setUserPath("");
				fl.setFileSize(null);
				
				this.fileRepository.save(fl);
			}catch(Exception e){
				
			}
		return SuccessFileId;
	}
	

}
