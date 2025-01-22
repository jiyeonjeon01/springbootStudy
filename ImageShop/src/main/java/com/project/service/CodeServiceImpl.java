package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.domain.CodeLabelValue;
import com.project.mapper.CodeMapper;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeMapper mapper;

// 그룹코드 목록 조회 구현 
	@Override
	public List<CodeLabelValue> getCodeGroupList() throws Exception {
		return mapper.getCodeGroupList();
	}

	// 지정된 그룹코드에 해당하는 코드 목록 조회
	@Override
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
		return mapper.getCodeList(groupCode);
	}
}