package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class HelloLombok {

	private final String hello;
	private final int lombok;

	// 롬복 적용 확인용 예시
	// final 사용시 Setter 사용불가능
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("Hello", 5);
//		helloLombok.setHello("헬로");
//		helloLombok.setLombok(5);

		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());

	}

}
