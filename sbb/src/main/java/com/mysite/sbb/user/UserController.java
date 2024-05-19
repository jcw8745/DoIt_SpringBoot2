package com.mysite.sbb.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	
	@ModelAttribute("genders")
	public Genders[] genders() {
	    return Genders.values();
	}
	
	@ModelAttribute("itemTypes")
	public ItemType[] itemTypes() {
	    return ItemType.values(); // // enum.values를 하면 저장되어있는 값들을 배열로 넘겨준다.
	}
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm,Model model) {
		

	    model.addAttribute("item",genders()); 
		return "signup_form";
		
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2","passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
			return "signup_form";
		}
		
		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed","이미 등록된 사용자입니다.");
			return "signup_form";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed",e.getMessage());
			return "signup_form";			
		}
		
		return "redirect:/";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}


}
