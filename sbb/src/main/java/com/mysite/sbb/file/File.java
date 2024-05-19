package com.mysite.sbb.file;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class File {
	
	  @Id
	  @GeneratedValue(generator = "uuid2")
	  @GenericGenerator(name = "uuid2", strategy = "uuid2")
	  @Column(columnDefinition = "BINARY(16)")
	private String fileId;
	
	private String fileName;
	
	private String creatorId;
	
	private String extention;
	
	private String location;
	
	private String serverPath;
	
	private String userPath;
}
