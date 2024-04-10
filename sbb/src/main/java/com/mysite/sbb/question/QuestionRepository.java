package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	//타이틀로 단건 찾기
	Question findBySubject(String subjString);
	
	// 타이틀, 내용으로 찾기
	Question findBySubjectAndContent(String subjString , String Content);
	
	//타이틀로 찾기
	List<Question> findBySubjectLike(String subject);
	
	//페이징
	Page<Question> findAll(Pageable pageable);
	
	//
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);

}
