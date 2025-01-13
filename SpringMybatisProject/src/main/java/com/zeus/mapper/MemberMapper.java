package com.zeus.mapper;

import java.util.List;

import com.zeus.domain.Board;
import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;

public interface MemberMapper {
	public void create(Member member) throws Exception;

	public void createAuth(MemberAuth memberAuth) throws Exception;
}