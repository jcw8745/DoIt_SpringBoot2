package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum ItemType {

	BOOK("책"),FOOD("음식"),ETC("기타");
	private final String description;
	
	ItemType(String description){
		this.description = description;
	}
}
