package com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zeus.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		log.info("login get");
		return "loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(String userId, String userPw, Model model) {
		log.info("login post");
		log.info("login userId = " + userId);
		log.info("login userPw = " + userPw);
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPw(userPw);
		member.setUserName("제우스");
		member.setEmail("zeus@zeus.com");
		//모델에 "user" member 등록
		model.addAttribute("user", member);
	}

}