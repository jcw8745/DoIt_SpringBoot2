package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	

	private final QuestionRepository questionRepository;
	//.properties 값 가져오기
	@Autowired
    Environment env;
	
	private Specification<Question> search(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true); //중복을 제거
				Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
				Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
				Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
				return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
						cb.like(q.get("content"), "%" + kw + "%"),		// 내용
						cb.like(u1.get("username"), "%" + kw + "%"),	// 질문 작성자
						cb.like(a.get("content"),  "%" + kw + "%"),		// 답변 내용
						cb.like(u2.get("username"), "%" + kw + "%"));  // 답변 작성자
				
			}
		};
	}

	
	public Page<Question> getList(int page, String kw){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts));
		Specification<Question> spec = search(kw);
		return this.questionRepository.findAllByKeyword(kw, pageable);
	}
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
		
	}
	
	public void create(String subject, String content, SiteUser user ,MultipartFile fileInfo) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		
		String makeFileId = "";
		
		
		//TODO 파일아이디 생성후 파일등록 API 호출
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		// API 호출할 URL 지정
		String apiUrl = env.getProperty("fileApiUrl");
	    // 요청에 포함될 데이터
	    // 멀티파트 요청을 위한 데이터 설정
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("fileInfo", fileInfo.getResource());
        body.add("user", user.getUsername());
        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        // HttpEntity 객체 생성 (요청 데이터와 헤더 포함)
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        // POST 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
        // 응답 데이터 출력
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseData = responseEntity.getBody();
            System.out.println("Response from API: " + responseData);
            makeFileId = responseData;//TODO 리스폰스데이터에 파일아이디 줌
        } else {
            System.out.println("Error occurred: " + responseEntity.getStatusCodeValue());
        }
        
        q.setFileId(makeFileId);
        
		this.questionRepository.save(q);
	}
	
	public Page<Question> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	public void vote(Question question,SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}

}
