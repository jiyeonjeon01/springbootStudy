package com.zeus.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeus.domain.Board;
import com.zeus.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
@MapperScan(basePackages = "com.zeus.mapper")
public class BoardController {
	@Autowired
	private BoardService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Board board, Model model) throws Exception {
		log.info("registerForm");
	}

	// 1. 제목에 값을 입력하지 않아서 유효성 검증 예외발생 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Validated Board board, BindingResult result, Model model) throws Exception {
		log.info("register");
		
		if(result.hasErrors()) {
			return "board/register";
		}
		service.register(board);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "board/success";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		log.info("list");
		model.addAttribute("list", service.list());
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		log.info("read");
		// 2. 상세 게시글 요청 번호가 잘못 넣어졌을 때 예외 발생 
		model.addAttribute(service.read(boardNo));
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		log.info("remove");
		// 3. 마이바티스에서 게시글 삭제 진행 쿼리 컬럼명이 잘못되었을 때 예외 발생 
		service.remove(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "board/success";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		log.info("modifyForm");
		model.addAttribute(service.read(boardNo));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Board board, Model model) throws Exception {
		log.info("modify");

		service.modify(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "board/success";
	}
}
