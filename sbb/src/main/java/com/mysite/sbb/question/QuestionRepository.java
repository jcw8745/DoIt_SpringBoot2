package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	Question findBySubject(String subjString);
	Question findBySubjectAndContent(String subjString , String Content);
	List<Question> findBySubjectLike(String subject);

}
