package com.project.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.common.security.domain.CustomUser;
import com.project.domain.Member;
import com.project.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Load User By UserName : " + username);
		// userName은 사용자명이 아니라 사용자 아이디이다.
		Member member = mapper.readByUserId(username);
//		log.info("queried by member mapper: " + member);

		return member == null ? null : new CustomUser(member);

	}

}