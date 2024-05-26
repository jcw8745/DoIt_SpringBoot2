package com.mysite.sbb.file;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@PropertySource("/config.properties")


@Slf4j
@RequestMapping("/jcwfiles")
@RequiredArgsConstructor
@RestController
public class FileController {
	
	private final UserService userService;
	private final FileService fileService;
	
	//.properties 값 가져오기
	@Autowired
    Environment env;
	

	@GetMapping("/uploadFile")
//	@ResponseBody
	public String uploadFileForm(){
		return "uploadfile_form";
	}
	
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("fileInfo") MultipartFile fileInfo,@RequestParam("user") String user) {
		
        // 요청 바디를 받아와서 처리
        System.out.println("Received POST request with body:--fileInfo: "+fileInfo.getResource());
        //TODO 서비스는 return값 (String)fileId로  api 호출한곳으로는 responseData로 구현
        
        // 처리 결과를 클라이언트에 응답
        String responseData = fileService.uploadallfile(fileInfo, "" , user); //TODO fileId 보내기
        if(!responseData.toString().equals("FAIL")) {
        	return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
	    
	}
	
	@PostMapping("/apitest")
	public ResponseEntity<String> handlePostRequest( @RequestParam("fileInfo") MultipartFile fileInfo){
		
        // 요청 바디를 받아와서 처리
        System.out.println("Received POST request with body:--fileInfo: "+fileInfo.getResource());
        //TODO 서비스는 return값 (String)fileId로  api 호출한곳으로는 responseData로 구현 ㅇㅇ dddd dddd 서피스
        
        // 처리 결과를 클라이언트에 응답
        String responseData = "Received POST request successfully!"; //TODO fileId 보내기
        return new ResponseEntity<>(responseData, HttpStatus.OK);
	    
	}

}
