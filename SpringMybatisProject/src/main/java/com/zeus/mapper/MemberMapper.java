package com.zeus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeus.domain.Board;
import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;

public interface MemberMapper {
	public void create(Member member) throws Exception;

	public void createAuth(MemberAuth memberAuth) throws Exception;

	public List<Member> list() throws Exception;

	public Member read(int userNo) throws Exception;

	public void update(Member member) throws Exception;

	public void delete(int userNo) throws Exception;

	public void deleteAuth(int userNo) throws Exception;
	
	public List<Member> search(@Param("userId") String userId) throws Exception;
}