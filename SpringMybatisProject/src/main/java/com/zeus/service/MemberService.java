package com.zeus.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeus.domain.Board;
import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;

public interface MemberService {
	public void register(Member member) throws Exception;

	public Member read(int userNo) throws Exception;

	public void modify(Member member) throws Exception;

	public void remove(int userNo) throws Exception;

	public List<Member> list() throws Exception;
	
	public List<Member> search(@Param("userId") String userId) throws Exception; 
}
