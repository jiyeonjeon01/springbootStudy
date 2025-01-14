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
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String search(String userId, Model model) throws Exception {
		log.info("search");
		Member member = new Member();
		member.setUserId(userId);
		
		model.addAttribute("member", member);
		model.addAttribute("list", service.search(userId));
		return "user/list";
	}

	// 사용자 입력 폼 요청(/WEB-INF/views/user/register.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Member member, Model model) throws Exception {
		// 폼에 등록하려면 member를 줘야한다 
		log.info("UserRegisterForm");
	}

	// 사용자 입력 내용을 db에 저장 후 성공이면(/WEB-INF/views/user/success.jsp)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Member member, Model model) throws Exception {
		service.register(member);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "user/success";
	}

	// 사용자 정보 리스트 요청 (/WEB-INF/views/user/list.jsp)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	// 사용자 정보 상세 내용 요청 (/WEB-INF/views/user/read.jsp)
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(int userNo, Model model) throws Exception {
		model.addAttribute(service.read(userNo));
	}

	// 사용자 삭제 요청 (/WEB-INF/views/user/success.jsp)
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(int userNo, Model model) throws Exception {
		service.remove(userNo);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "user/success";
	}

	// 사용자 수정 폼 요청 (/WEB-INF/views/user/modify.jsp)
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(int userNo, Model model) throws Exception {
		model.addAttribute(service.read(userNo));
	}

	// 사용자 수정 내용 db에 저장 요청 (/WEB-INF/views/user/success.jsp)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member member, Model model) throws Exception {
		service.modify(member);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "user/success";
	}
}
