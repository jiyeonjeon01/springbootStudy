package com.zeus.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Board {
	private int boardNo; 
	private String title; 
	private String content; 
	private String writer; 
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 
	private Date regDate; 
}
