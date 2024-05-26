package com.mysite.sbb.file;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FileTb {
	
	  @Id
	private String fileId;
	
	private String fileName;
	
	private String creatorId;
	
	private String extention;
	
	private String location;
	
	private String serverPath;
	
	private String userPath;
	
	private Byte fileSize;
}
