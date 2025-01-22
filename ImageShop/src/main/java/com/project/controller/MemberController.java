package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.common.domain.CodeLabelValue;
import com.project.domain.Member;
import com.project.service.CodeService;
import com.project.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private CodeService codeService;

	// 스프링 시큐리티의 비밀번호 암호처리기
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 등록 페이지
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Member member, Model model) throws Exception {
		// 직업코드 대분류 코드에서 해당된 학과 코드목록(사용할 수 잇어야 함)을 조회하여 뷰에 전달
		String groupCode = "001";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
	}

	// 등록 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Validated Member member, BindingResult result, Model model, RedirectAttributes rttr)
			throws Exception {
		if (result.hasErrors()) {
			// 직업코드 목록을 조회하여 뷰에 전달
			String groupCode = "001";
			List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

			model.addAttribute("jobList", jobList);

			return "user/register";
		}
		// 비밀번호 암호화
		// 123456
		// passwordEncoder.encode(123456)
		// 8643aa23-77df-4c49-b9b2-321e23516a9c
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));

		service.register(member);

		rttr.addFlashAttribute("userName", member.getUserName());
		return "redirect:/user/registerSuccess";
	}

	// 등록 성공 페이지
	@RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
	public void registerSuccess(Model model) throws Exception {
	}

	// 목록 페이지
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	// 상세 페이지
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(int userNo, Model model) throws Exception {
		// 직업코드 목록을 조회하여 뷰에 전달
		String groupCode = "001";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

		model.addAttribute("jobList", jobList);
		model.addAttribute(service.read(userNo));
	}

	// 수정 페이지
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(int userNo, Model model) throws Exception {
		// 직업코드 목록을 조회하여 뷰에 전달
		String groupCode = "001";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
		model.addAttribute(service.read(userNo));
	}

	// 수정 처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member member, RedirectAttributes rttr) throws Exception {
		service.modify(member);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}

	// 삭제 처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(int userNo, RedirectAttributes rttr) throws Exception {
		service.remove(userNo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}

	// 회원 테이블에 데이터가 없으면 최초 관리자를 생성한다.
	@RequestMapping(value = "/setup", method = RequestMethod.POST)
	public String setupAdmin(Member member, RedirectAttributes rttr) throws Exception {
		// 회원 테이블 데이터 건수를 확인하여 빈 테이블이면 최초 관리자를 생성한다.
		if (service.countAll() == 0) {
			String inputPassword = member.getUserPw();
			member.setUserPw(passwordEncoder.encode(inputPassword));
			member.setJob("00");
			service.setupAdmin(member);
			rttr.addFlashAttribute("userName", member.getUserName());
			return "redirect:/user/registerSuccess";
		}
		// 회원 테이블에 데이터가 존재하면 최초 관리자를 생성할 수 없으므로 실패 페이지로 이동한다.
		return "redirect:/user/setupFailure";
	}

	// 최초 관리자를 생성하는 화면을 반환한다.
	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public String setupAdminForm(Member member, Model model) throws Exception {
		// 회원 테이블 데이터 건수를 확인하여 최초 관리자 등록 페이지를 표시한다.
		if (service.countAll() == 0) {
			return "user/setup";
		}
		// 회원 테이블에 데이터가 존재하면 최초 관리자를 생성할 수 없으므로 실패 페이지로 이동한다.
		return "user/setupFailure";
	}
}
