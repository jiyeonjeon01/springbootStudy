package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.Board;
import com.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	public BoardService service;

	@RequestMapping(value = "/registerForm", method = RequestMethod.GET)
	public String registerForm(Board board, Model model) throws Exception {
		log.info("registerForm");
		return "board/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Board board, Model model) throws Exception {
		log.info("register");
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
	public void read(int boardNo, Model model) throws Exception {
		log.info("read");
		log.info("read boardNo = " + boardNo);
		Board board = new Board();
		board.setBoardNo(boardNo);
		model.addAttribute(service.read(board.getBoardNo()));
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int boardNo, Model model) throws Exception {
		log.info("remove");
		Board board = new Board();
		board.setBoardNo(boardNo);
		service.remove(board.getBoardNo());
		model.addAttribute("msg", "삭제를 성공하였습니다.");
		
		return "board/success";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Board board, Model model) throws Exception {
		log.info("modify");
		service.modify(board);
			model.addAttribute("msg", "수정이 완료되었습니다.");
		return "board/success";
	}

}