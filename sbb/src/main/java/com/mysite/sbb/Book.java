package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Book {
	
private String title;
private String author;



// 롬복 적용 확인용 예시
public static void main(String[] args) {
	Book book = new Book();
	book.setTitle("헬로");
	book.setAuthor("dd");
	
	System.out.println(book.getTitle());
	System.out.println(book.getAuthor());
	
}
 
}
