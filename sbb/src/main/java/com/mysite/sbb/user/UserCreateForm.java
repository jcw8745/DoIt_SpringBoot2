package com.mysite.sbb.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	
	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String username;

	@Size(min = 7, max = 20)
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
	private String password2;

	@NotEmpty(message = "이메일은 필수 항목입니다.")
	private String email;
	
	@Size(min = 2, max = 10)
	private String nickName;
	
	@NotEmpty(message = "생년월일은 필수 항목입니다.")
	private String birth;
	
	@NotEmpty(message = "전화번호는 필수 항목입니다.")
	private String phoneNumber;
	
	private String gender;
	 	public String getGenders() {
	        return gender;
	    }
	    public void setGenders(String gender) {
	        this.gender = gender;
	    }
	
	private String address1;
	
	private String address2;
	
	 private String itemType;
	 	public String getItemType() {
	        return itemType;
	    }
	    public void setItemType(String itemType) {
	        this.itemType = itemType;
	    }

}
