package com.zeus.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	private int userNo; 
	private String userId; 
	private String userPw; 
	private String userName; 
	private Date regDate; 
	private Date updDate; 
	 
	private List<MemberAuth> authList;
}
