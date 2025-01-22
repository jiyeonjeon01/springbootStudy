package com.project.mapper;

import java.util.List;

import com.project.common.domain.CodeLabelValue;

public interface CodeMapper {
	// 그룹코드 목록 조회
	public List<CodeLabelValue> getCodeGroupList() throws Exception;

	// 지정된 그룹코드에 해당하는 코드 목록 조회
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;
}
