package com.mysite.sbb.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

	
	@GetMapping("/popup")
	public String makePopup() {
	    return "noticeModal";
	}
	
}
