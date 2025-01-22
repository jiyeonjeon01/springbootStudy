package com.project.service;

import java.util.List;

import com.project.domain.Member;

public interface MemberService {
	// 등록 처리
	public void register(Member member) throws Exception;

	// 목록 페이지
	public List<Member> list() throws Exception;

	// 상세 페이지
	public Member read(int userNo) throws Exception;

	// 수정 처리
	public void modify(Member member) throws Exception;

	// 삭제 처리
	public void remove(int userNo) throws Exception;

	// 회원 테이블의 데이터 건수를 반환한다.
	public int countAll() throws Exception;

	// 최초 관리자 생성을 위한 데이터를 등록한다.
	public void setupAdmin(Member member) throws Exception;
}
