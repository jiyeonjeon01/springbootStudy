package com.project.service;

import java.util.List;

import com.project.domain.CodeDetail;

public interface CodeDetailService {
	// 등록 처리
	public void register(CodeDetail codeDetail) throws Exception;

	// 목록 페이지
	public List<CodeDetail> list() throws Exception;

	// 상세 페이지(수정 페이지)
	public CodeDetail read(CodeDetail codeDetail) throws Exception;

	// 수정 처리
	public void modify(CodeDetail codeDetail) throws Exception;

	// 삭제 처리
	public void remove(CodeDetail codeDetail) throws Exception;
}
