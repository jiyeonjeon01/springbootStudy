package com.zeus.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeus.domain.Board;
import com.zeus.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("환영합니다. 클라이언트 지역은 " + locale + "이다.");
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
		String formattedNow = now.format(formatter);
		model.addAttribute("serverTime", formattedNow);
		return "home";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
		log.info("환영합니다. 클라이언트 지역은 " + locale + "이다.");
		String[] args = { "이순신" };
		String[] args2 = { "Mr.Lee" };
		// 스프링 프레임워크로부터 MessageSource를 주입 받은 다음 getMessage 메서드를 호출한다.
		String message = messageSource.getMessage("welcome.message", args, Locale.KOREAN);
		String message2 = messageSource.getMessage("welcome.message", args2, Locale.ENGLISH);
		log.info("Welcome message : " + message);
		log.info("Welcome message2 : " + message2);
		return "home";
	}
}
