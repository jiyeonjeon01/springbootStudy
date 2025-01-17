package com.zeus.domain;

import lombok.Data;

@Data
public class MemberAuth {
	private static final long serialVersionUID = 4948993673467321353L;
	private int userNo;
	private String auth;
}