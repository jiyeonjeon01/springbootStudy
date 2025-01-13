package com.zeus.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Setter;


@Data
public class MemberAuth { 
 
	private int userNo; 
	private String auth; 
 
} 