package com.project.common.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.project.common.security.domain.CustomUser;
import com.project.domain.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		CustomUser customUser = (CustomUser) auth.getPrincipal();
		Member member = customUser.getMember();
		log.info("Userid = " + member.getUserId());
		// 이전에 저장된 요청을 가져온다.
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			// 저장된 요청이 없으면 기본 페이지로 이동한다.
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		// 저장된 요청으로 리다이렉트한다.
		String targetUrl = savedRequest.getRedirectUrl();
		response.sendRedirect(targetUrl);

	}

	// 인증 과정에서 발생한 예외 정보를 세션에서 제거합니다.
	private void clearAuthenticationAttribute(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		// 세션에서 인증 예외 속성을 제거한다.
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}