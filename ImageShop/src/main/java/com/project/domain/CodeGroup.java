package com.project.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CodeGroup {
	private String groupCode;
	private String groupName;
	private String useYn;
	private Date regDate;
	private Date updDate;
}
