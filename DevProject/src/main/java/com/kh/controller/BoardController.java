package com.kh.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.domain.Board;

import jakarta.websocket.server.PathParam;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@PutMapping(value = "/{boardno}")
	public ResponseEntity<String> modify(@PathVariable("boardno") int boardno, @RequestBody Board board, Model model) {
		log.info("put boardno = " + boardno);
		log.info("put board = " + board.toString());

		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}

	@PostMapping(value = "/{boardno}")
	public ResponseEntity<String> modifyPost(@PathVariable("boardno") int boardno, @RequestBody Board board) {
		log.info("post boardno = " + boardno);
		log.info("post board = " + board.toString());

		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}

	@GetMapping(value = "/{boardno}", produces = "application/json")
	public ResponseEntity<Board> boardGetOne(@PathVariable("boardno") int boardno) {
		log.info("post boardno = " + boardno);
		Board board = new Board();

		board.setTitle("제목");
		board.setContent("내용입니다.");
		board.setWriter("홍길동");
		board.setRegDate(new Date());
		
		ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);
		return entity;
	}

}