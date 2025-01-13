package com.zeus.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeus.domain.Board;
import com.zeus.domain.Member;
import com.zeus.service.BoardService;
import com.zeus.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
@MapperScan(basePackages = "com.zeus.mapper")
public class MemberController {
	// 서비스 이용해서 DB 접근
	@Autowired
	private MemberService service;

	// 사용자 입력 폼 요청(/WEB-INF/views/user/register.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public void registerForm(Member member, Model model) throws Exception { 
	log.info("UserRegisterForm"); 
	} 
	
	// 사용자 정보를 db에 입력 요청(/WEB-INF/views/user/success.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.POST) 
	public String register(Member member, Model model) throws Exception { 
	service.register(member); 
	model.addAttribute("msg", "등록이 완료되었습니다."); 
	return "user/success"; 
	}

}
