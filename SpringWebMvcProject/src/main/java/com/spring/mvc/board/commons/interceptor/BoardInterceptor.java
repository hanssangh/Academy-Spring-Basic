package com.spring.mvc.board.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//인터셉터 클래스를 만들려면 HandlerInterceptor 인터페이스를 구현합니다.
public class BoardInterceptor implements HandlerInterceptor  {

	//preHandle은 컨트롤러로 들어가기 전 처리해야 할 로직을 작성.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("게시판 인터셉터 발동!: preHandle");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			System.out.println("회원 인증 실패!");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String htmlCode = "<script> \r\n"
					+ "alert('로그인이 필요한 페이지 입니다.'); \r\n"
					+ "location.href='/board/list'; \r\n"
					+ "</script>";
			
			out.print(htmlCode);
			out.flush();
			
			return false; //false를 리턴하면 컨트롤러로 요청이 전달되지 않습니다.
		}
		
		System.out.println("회원 인증 성공!");
		return true; //true를 리턴하면 아무 일도 일어나지 않고 요청이 전달됩니다.
	}
	
	//postHandle은 컨트롤러를 나갈 때 공통 처리해야 할 내용을 작성.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("게시판 인터셉터 발동!: postHandle");
		
		System.out.println("모델 객체의 내부: " + modelAndView.getModel());
		
		Object data = modelAndView.getModel().get("article");
		System.out.println("article이라는 이름의 데이터: " + data);
		System.out.println("뷰페이지 이름: " + modelAndView.getViewName());
		
		/*
		 컨트롤러에서 로직을 처리하고 나가는 흐름을 붙잡아서
		 모델 데이터가 제대로 전송이 되는 지 확인하고, 추가할 내용이나 수정할 내용이 있다면
		 모델 객체를 받아와서 추가, 수정도 가능합니다.
		 기타 특징을 이용하여 흐름을 제어할 수도 있습니다. (sendRedirect, viewName을 수정)
		 */
		
	}
	
	
}














