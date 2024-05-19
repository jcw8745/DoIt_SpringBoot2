package com.mysite.sbb.user;

import lombok.Getter;
@Getter
public enum Genders {

	MALE("남자"),FEMALE("여자");
	private final String gender;
	
	Genders(String gender){
		this.gender = gender;
	}
}
