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
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
		
		int result=0;
		
		//.properties 값 가져오기
		String uploadsDir=env.getProperty("AllFileLocation");
		//TODO 서비스 호출하여 반환받은 값으로 성공 실패 처리

		
		 result = fileService.uploadallfile(file,uploadsDir);
	       
	        return "ddddddddddddddddddddddddddddddddddddddddddddddd";
	    
	}
	
	@PostMapping("/apitest")
	public ResponseEntity<String> handlePostRequest(@RequestBody String requestBody){
		
        // 요청 바디를 받아와서 처리
        System.out.println("Received POST request with body: " + requestBody);
        //TODO 서비스는 return값 0,1로  api 호출한곳으로는 responseData로 구현
        
        // 처리 결과를 클라이언트에 응답
        String responseData = "Received POST request successfully!";
        return new ResponseEntity<>(responseData, HttpStatus.OK);
	    
	}

}
