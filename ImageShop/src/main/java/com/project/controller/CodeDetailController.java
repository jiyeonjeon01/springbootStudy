package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.common.domain.CodeLabelValue;
import com.project.domain.CodeDetail;
import com.project.service.CodeDetailService;
import com.project.service.CodeService;

@Controller
@RequestMapping("/codedetail")
public class CodeDetailController {

	@Autowired
	private CodeDetailService codeDetailService;

	@Autowired
	// 사용되고 있는 그룹코드 정보 관리 서비스
	private CodeService codeService;

	// 등록 페이지
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model) throws Exception {
		CodeDetail codeDetail = new CodeDetail();
		model.addAttribute(codeDetail);

		// 그룹코드 목록을 조회하여 뷰에 전달(사용허가 설정이 돼잇는 그룹 코드만 가져온다)
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	// 등록 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.register(codeDetail);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codedetail/list";
	}

	// 목록 페이지
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("list", codeDetailService.list());
	}

	// 상세 페이지
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(CodeDetail codeDetail, Model model) throws Exception {
		model.addAttribute(codeDetailService.read(codeDetail));
		// 그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	// 수정 페이지
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(CodeDetail codeDetail, Model model) throws Exception {
		model.addAttribute(codeDetailService.read(codeDetail));
		// 그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	// 수정 처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.modify(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/codedetail/list";
	}

	// 삭제 처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.remove(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codedetail/list";
	}
}
