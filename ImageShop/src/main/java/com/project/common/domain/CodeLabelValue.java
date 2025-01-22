package com.project.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 그룹코드에서 사용되고 있는 그룹코드(value)와 그룹이름(label) 관리 하는 도메인
@Data
public class CodeLabelValue {
	private final String value;
	private final String label;
}